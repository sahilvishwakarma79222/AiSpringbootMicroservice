package com.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().
                route("quizmongo-service", route -> route.path("/quizmongo/**")
                        .filters(f -> f.rewritePath("/quizmongo/?(?<remaining>.*)", "/${remaining}")
                        )
                        .uri("lb://QUIZMONGOSERVICE")
                ).
                route("categorypostgres-service", route -> route.path("/categorypostgres/**")
                        .filters(f -> f.rewritePath("/categorypostgres/?(?<remaining>.*)", "/${remaining}")
                        )
                        .uri("lb://CATEGORYPOSTGRESH")
                ).
                build();
    }

}
