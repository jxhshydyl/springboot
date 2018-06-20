package com.lwp.springboot.config;

import com.lwp.springboot.filter.MyFilter;
import com.lwp.springboot.interceptor.InterceptorConfig;
import com.lwp.springboot.listener.MyHttpSessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MywebConfig extends WebMvcConfigurerAdapter {
    /**
     * 注册拦截器
     * @return
     */
    @Bean
    public InterceptorConfig securityInterceptor() {
        return new InterceptorConfig();
    }
    /**
     * 拦截器设置拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("=====拦截器=====");
        registry.addInterceptor(new InterceptorConfig())
                .addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }

    /**
     * 过滤器
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
        frBean.addUrlPatterns("/*");
        System.out.println("=====过滤器=====");
        return frBean;
    }

    /**拦截器
     *
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        System.out.println("=====监听器=====");
        return srb;
    }
}