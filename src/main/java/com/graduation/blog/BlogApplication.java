package com.graduation.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.graduation.blog.dao")
@ComponentScan("com.graduation.blog")
@EnableAutoConfiguration
@EnableTransactionManagement
public class BlogApplication {


  private CorsConfiguration buildConfig() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    // 这两句不加不能跨域上传文件
    corsConfiguration.setAllowCredentials(true);
    // 加上去就可以了
    corsConfiguration.setMaxAge(3600L);
    return corsConfiguration;
  }

  /**
   * 跨域过滤器
   * @return
   */
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", buildConfig());
    return new CorsFilter(source);
  }


  public static void main(String[] args) {
    SpringApplication.run(BlogApplication.class, args);
  }

}

