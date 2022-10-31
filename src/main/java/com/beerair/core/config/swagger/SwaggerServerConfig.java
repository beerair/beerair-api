package com.beerair.core.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

@Component
public class SwaggerServerConfig implements WebMvcOpenApiTransformationFilter {
    @Value("${swagger.url}")
    private String url;

    @Value("${swagger.desc}")
    private String desc;

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();
        openApi.setServers(servers());
        return openApi;
    }

    private List<Server> servers() {
        Server server = new Server();
        server.setDescription(desc);
        server.setUrl(url);
        return Collections.singletonList(server);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return documentationType.equals(DocumentationType.OAS_30);
    }
}
