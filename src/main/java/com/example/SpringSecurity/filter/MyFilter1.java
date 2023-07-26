package com.example.SpringSecurity.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (httpServletRequest.getMethod().equals("POST")) {
            System.out.println("POST 요청됨");
            String headerAuth = httpServletRequest.getHeader("Authorization");
            System.out.println(headerAuth);
            if (headerAuth.equals("cos")) {
                System.out.println("인증됨");
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                System.out.println("인증안됨");
                return;
            }
        }

        System.out.println("===================필터1");
    }
}
