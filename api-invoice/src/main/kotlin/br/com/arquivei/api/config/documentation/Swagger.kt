package br.com.arquivei.api.config.documentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class Swagger {
    
    @Bean
    fun greetingApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.arquivei"))
            .build()
            .apiInfo(details())

    }

    private fun details(): ApiInfo {
        return ApiInfoBuilder()
            .title("Invoice REST API")
            .description("API for Invoices by Access key")
            .version("1.0.0")
            .build()
    }
}