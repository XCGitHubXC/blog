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

  /**
   * 财务角色
   */
  private static final String ROLE_FINANCE = "FINANCE";
  /**
   * 领导角色
   */
  private static final String ROLE_LEADER = "LEADER";
  /**
   * 办案秘书角色
   */
  private static final String ROLE_SECRETARY = "SECRETARY";
  /**
   * 仲裁员角色
   */
  private static final String ROLE_ARBITRATION = "ARBITRATION";
  /**
   * 秘书长角色
   */
  private static final String ROLE_SECRETARY_GENERAL = "SECRETARY_GENERAL";
  /**
   * 系统管理员角色
   */
  private static final String ROLE_ADMIN = "ADMIN";

  @Resource
  private JWTAuthenticationFilter jwtAuthenticationFilter;
  @Resource
  private JWTAuthenticationProvider jwtAuthenticationProvider;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(DefaultErrorController.ERROR_PATH, "/auth/**", "/common/**", "/api1/**",
            "/resources/static/**",
            "/download*", "/sign/jump/**", "/wpUsers/select/**", "/swagger-ui.html", "/webjars/**",
            "/swagger-resources/**",
            "/v2/**", "/api/areas/**", "/libra/**", "/api/fileInfo/download/**", "/druid/**",
            "/attachment/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().and().exceptionHandling()
        .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
        .accessDeniedHandler(new JWTAccessDeniedHandler()).and()
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
        .authenticationProvider(jwtAuthenticationProvider).authorizeRequests()

        .antMatchers("/user/**", "/authRoleRelation/**", "/dictionary/**", "/menu/**", "/role/**",
            "/userRoleRelation/**")
        .hasAnyAuthority(ROLE_ADMIN).antMatchers("/api/arbitrationcase/caseControl/FINANCE/**")
        .hasAnyAuthority(ROLE_FINANCE)
        .antMatchers("/api/arbitrationcase/caseControl/LEADER/**").hasAnyAuthority(ROLE_LEADER)
        .antMatchers("/api/arbitrationcase/caseControl/ARBITRATION/**")
        .hasAnyAuthority(ROLE_ARBITRATION)
        .antMatchers("/api/arbitrationcase/caseControl/SECRETARY/**")
        .hasAnyAuthority(ROLE_SECRETARY)
        .antMatchers("/api/arbitrationcase/caseControl/SECRETARY_GENERAL/**")
        .hasAnyAuthority(ROLE_SECRETARY_GENERAL)

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
