package com.everis.boundary;

import java.util.Arrays;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(title = "Everis-Car-App_API", version = "0.0", description = "Car's CRUD Functionality"))
public class JAXRSConfiguration extends Application  {
	
	@SuppressWarnings("rawtypes")
	public JAXRSConfiguration() {
        super();
        OpenAPI oas = new OpenAPI();
        oas.setServers(null);
        Server server = new Server();
        server.setUrl("http://localhost:8080/car-api");
        
        oas.setServers(Arrays.asList(server));
        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(oas)
                .prettyPrint(true)
                .resourcePackages(Stream.of("io.swagger.sample.resource").collect(Collectors.toSet()));

        try {
            new JaxrsOpenApiContextBuilder()
                .openApiConfiguration(oasConfig)
                .buildContext(true);
        } catch (OpenApiConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

}
