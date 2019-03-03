package com.graduation.blog.config;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket swaggerSpringMvcPlugin() {
//    ParameterBuilder tokenPar = new ParameterBuilder();
//    List<Parameter> pars = new ArrayList<>();
//    tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//    pars.add(tokenPar.build());
//    return new Docket(DocumentationType.SWAGGER_2)
//        .select()
//        .apis(RequestHandlerSelectors.any())
//        .paths(PathSelectors.any())
//        .build()
//        .globalOperationParameters(pars)
//        .apiInfo(apiInfo());
    ParameterBuilder tokenPar = new ParameterBuilder();
    List<Parameter> pars = new ArrayList<>();
    tokenPar.name("Authorization").description("令牌")
        .modelRef(new ModelRef("string"))
        .parameterType("header")
        .required(true).build();
    pars.add(tokenPar.build());
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .build()
        .globalOperationParameters(pars)
        .apiInfo(apiInfo());
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Boot中使用Swagger2构建RESTFUL APIS")
        .description("blog项目后台API接口文档")
        .version("1.0")
        .build();
  }


}
