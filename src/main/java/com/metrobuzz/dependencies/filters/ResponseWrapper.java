package com.metrobuzz.dependencies.filters;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrobuzz.dependencies.constants.ResponseConstants;
import com.metrobuzz.dependencies.models.CustomResponse;
import com.metrobuzz.dependencies.models.Response;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintWriter writer;
    private ServletOutputStream servletOutputStream;
    private HttpServletResponse httpServletResponse;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.httpServletResponse = response;
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    public void serveContent() throws IOException {

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        if (isTextResponse() || isApplicationJson()) {
            PrintWriter servletWriter = this.httpServletResponse.getWriter();

            String str = new String(byteArray, StandardCharsets.UTF_8);

            int status = getStatus();

            String message = ResponseConstants.getMessageFromCode(status);

            Object finalResponse;

            if (status >= 400) {
                String errorMessage = extractErrorMessage(str);

                finalResponse = this.getFinalResponse(status, errorMessage, null);
            } else {
                Object objectMapped = objectMapper.readValue(str, Object.class);

                finalResponse = this.getFinalResponse(status, message, objectMapped);
            }

            String serializedResponse = objectMapper.writeValueAsString(finalResponse);

            servletWriter.write(serializedResponse);
            servletWriter.close();
        } else {
            ServletOutputStream finalOutputStream = this.httpServletResponse.getOutputStream();
            finalOutputStream.write(byteArray);
            finalOutputStream.flush();
        }
    }

    public String extractErrorMessage(String responseBody) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
            responseMap.forEach((key, value) -> System.out.println(key));

            return (String) responseMap.get("errorMessage");

        } catch (IOException exception) {
            return "An unknown error has occurred";
        }

    }

    private static void printEntry(String key, String value) {
        System.out.println(key + ": " + value);
    }

    public Object getFinalResponse(int code, String message, Object mappedObject) {
        String statusCode = Integer.toString(code);

        if (statusCode.contains("20")) {
            return new Response<>(
                    message,
                    httpServletResponse.getStatus(), mappedObject);
        } else {
            return new CustomResponse(message, code);
        }
    }

    public boolean isApplicationJson() {
        String contentType = getContentType();
        return contentType != null && contentType.equals("application/json");
    }

    public boolean isTextResponse() {
        String contentType = getContentType();
        return contentType != null && contentType.startsWith("text/");
    }

    @Override
    public String getContentType() {
        return httpServletResponse.getContentType();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.writer == null) {
            this.writer = new PrintWriter(byteArrayOutputStream, true);
        }

        return this.writer;
    }

    @Override
    public ServletOutputStream getOutputStream() {

        if (this.servletOutputStream == null) {
            this.servletOutputStream = new ServletOutputStream() {
                public void write(int value) throws IOException {
                    byteArrayOutputStream.write(value);
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener listener) {
                }
            };
        }

        return this.servletOutputStream;
    }

}
