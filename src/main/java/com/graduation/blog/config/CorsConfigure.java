//package com.graduation.blog.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @Author: xiachuan
// * @Date: 2019/2/18
// * @Description:
// */
//@Configuration
//public class CorsConfigure extends WebMvcConfigurerAdapter {
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**")
//        .allowedOrigins("*")
//        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
//        .allowCredentials(true).maxAge(3600);
//  }
//}
