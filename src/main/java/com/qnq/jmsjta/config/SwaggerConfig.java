package com.qnq.jmsjta.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket postsApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("public-api")
        .apiInfo(apiInfo())
        .select()
        .paths(postPaths())
        .build();
  }

  private Predicate<String> postPaths() {
    return or(regex("/*.*"));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("jms-jta-service")
        .description("Contact Business Service")
        .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
        .contact(new Contact("Sourabh Verma", "", "sourabhkumar.verma@gmail.com"))
        .license("none")
        .licenseUrl("none")
        .version("1.0")
        .build();
  }
}
