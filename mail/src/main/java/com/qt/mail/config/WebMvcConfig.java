package com.qt.mail.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.qt.mail.common.utils.Constant;
import com.qt.mail.modules.api.interceptor.AuthorizationInterceptor;
import com.qt.mail.modules.api.resolver.LoginUserHandlerMethodArgumentResolver;

/**
 * MVC配置
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	// TODO Auto-generated method stub
    	//super.addViewControllers(registry);
       registry.addViewController( "/" ).setViewName( "forward:/login.html" );
	   registry.setOrder(Ordered.HIGHEST_PRECEDENCE );
	   super.addViewControllers( registry );
    }
    
    /**
     * 访问文件过滤
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/myFile/**").addResourceLocations("file:"+Constant.File_Base_Path);
    	super.addResourceHandlers(registry);
    }
    
}