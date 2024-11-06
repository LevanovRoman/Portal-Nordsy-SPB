package com.myapp.portalnordsyspb.trash;

import jakarta.servlet.*;

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
