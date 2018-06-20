package com.lwp.springboot.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyFilter implements Filter {
    private FilterConfig filterConfig = null;
    private boolean flag = true;
    private String charcode = "UTF-8";
 
    @Override  
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {  
        System.out.println("Spring boot 过滤器");
        System.out.println(servletRequest.getParameter("name"));
        HttpServletRequest hrequest = (HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        if(hrequest.getRequestURI().indexOf("/index") != -1 || 
                hrequest.getRequestURI().indexOf("/asd") != -1 ||
                hrequest.getRequestURI().indexOf("/online") != -1 ||
                hrequest.getRequestURI().indexOf("/login") != -1
                ) {
            filterChain.doFilter(servletRequest, servletResponse);  
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
            //wrapper.sendRedirect("/login");
        }
    }  
    @Override  
    public void destroy() {  
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String temp = this.filterConfig.getInitParameter("flag");
        String str = this.filterConfig.getInitParameter("charcode");
        if (temp != null) {
            if (temp.equals("true")) {
                this.flag = true;
            } else if (temp.equals("false")) {
                this.flag = false;
            } else {
                this.flag = true;
            }
        }
        if (str != null) {
            this.charcode = str;
        }
    }    
}  
