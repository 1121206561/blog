package com.jxnd.yuhaojun.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
//@EnableWebMvc
@Component
//设置配置类,服务器进行启动时初始化该类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptors sessionInterceptors;

    //设置拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptors).addPathPatterns("/**");
    }
}
