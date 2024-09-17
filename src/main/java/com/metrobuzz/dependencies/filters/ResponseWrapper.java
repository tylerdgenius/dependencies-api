package com.metrobuzz.dependencies.filters;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrobuzz.dependencies.models.Response;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintWriter writer;
    private ServletOutputStream servletOutputStream;
    private HttpServletResponse httpServletResponse;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.httpServletResponse = response;
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    public void serveContent() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        byte[] byteArray = byteArrayOutputStream.toByteArray();

        if (isTextResponse() || isApplicationJson()) {
            PrintWriter servletWriter = this.httpServletResponse.getWriter();

            String str = new String(byteArray, StandardCharsets.UTF_8);

            Object objectMapped = objectMapper.readValue(str, Object.class);

            Response<Object> customResponse = new Response<>(getMessage(httpServletResponse.getStatus()),
                    httpServletResponse.getStatus(), objectMapped);

            String serializedResponse = objectMapper.writeValueAsString(customResponse);

            servletWriter.write(serializedResponse);
            servletWriter.close();
        } else {
            ServletOutputStream finalOutputStream = this.httpServletResponse.getOutputStream();
            finalOutputStream.write(byteArray);
            finalOutputStream.flush();
        }
    }

    public String getMessage(int code) {
        String message = Integer.toString(code);

        return message.contains("20") ? "Successfully treated your request" : "Unable to proceeed with your request";
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
