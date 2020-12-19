package com.wyj.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 拦截 /admin 下的页面，
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 添加拦截页面
                .addPathPatterns("/admin/**")
                // 排除 /admin 页
                .excludePathPatterns("/admin")
                // 排除 /admin/login 页
                .excludePathPatterns("/admin/login");

    }
}
