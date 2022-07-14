package com.zap.lotes.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String URL_CONTA_ZAP = "https://www.contazap.com.br";
    private static final String ZAP_TEC = "ZAP Tecnologias";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(informacoesApi())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo informacoesApi() {
        ApiInfoBuilder appInfo = new ApiInfoBuilder();

        appInfo.title("ZAP lotes - Api");
        appInfo.description("API Rest para disponibilizar os serviços de lotes da Conta Zap");
        appInfo.version("1.0");
        appInfo.termsOfServiceUrl("Termo de uso: Direitos reservados - " + ZAP_TEC);
        appInfo.license("ZAP Tecnologia");
        appInfo.licenseUrl(URL_CONTA_ZAP);
        appInfo.contact(this.contato());
        return appInfo.build();
    }
    private Contact contato() {
        return new Contact(ZAP_TEC, URL_CONTA_ZAP, "tecnologia@contazap.com.br");
    }
}
