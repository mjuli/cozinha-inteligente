package br.com.fiap.cozinha_inteligente.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(
                    new Info().title("Cozinha Inteligente API")
                            .description("Projeto desenvolvido durante a Fase 01 do curso Arquitetura e Desenvolvimento em JAVA")
                            .version("v0.0.1")
                            .license(new License().name("Apache 2.0").url("https://github.com/mjuli"))
            );
  }

}
