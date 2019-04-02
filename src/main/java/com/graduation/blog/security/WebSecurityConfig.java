package com.graduation.blog.security;

import com.graduation.blog.controller.DefaultErrorController;
import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



  @Resource
  private JWTAuthenticationFilter jwtAuthenticationFilter;
  @Resource
  private JWTAuthenticationProvider jwtAuthenticationProvider;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(DefaultErrorController.ERROR_PATH, "/auth/**",
            "/common/**", "/api1/**", "/resources/static/**",
            "/user/userLogin/**", "/user/userRegister/**",
            "/recommend/**", "/download*", "/swagger-ui.html",
            "/webjars/**", "/article/blogSearch/**",
            "/swagger-resources/**", "/v2/**", "/api/areas/**",
            "/api/fileInfo/download/**", "/attachment/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().and().exceptionHandling()
        .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
        .accessDeniedHandler(new JWTAccessDeniedHandler()).and()
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
        .authenticationProvider(jwtAuthenticationProvider).authorizeRequests()
        .filterSecurityInterceptorOncePerRequest(true).anyRequest().authenticated().and().logout()
        .logoutUrl("/logout")
        .addLogoutHandler(new JWTLogoutHandler())
        .logoutSuccessHandler(new JWTLogoutSuccessHandler());
  }


  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
