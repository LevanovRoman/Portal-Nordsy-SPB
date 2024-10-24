package com.myapp.portalnordsyspb.auth.config;

import jakarta.servlet.*;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

//@Configuration
public class EncodingFilterConfig implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter(request, response);

    }
}
