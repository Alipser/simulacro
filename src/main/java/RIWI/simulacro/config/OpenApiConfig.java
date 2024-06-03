package RIWI.simulacro.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuracion de swagger
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Simulacro", version = "0.1", description = "Endpoints To be Consumed. This API allow different transctions to the backend"))
public class OpenApiConfig {

}
