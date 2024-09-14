package com.metrobuzz.dependencies.filters;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.metrobuzz.dependencies.wrappers.ResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class DefaultResponse extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        filterChain.doFilter(request, responseWrapper);

        responseWrapper.serveContent();
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }

}
