package com.apigateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class ApiConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().
                route("quizmongo-service", route -> route.path("/quizmongo/**")
                        .filters(f -> f.rewritePath("/quizmongo/?(?<remaining>.*)", "/${remaining}")
                                .retry(retry->retry.setMethods(HttpMethod.GET).setRetries(3)
                                        .setBackoff(Duration.ofMillis(50),Duration.ofMillis(600),2,true))
                        )
                        .uri("lb://QUIZMONGOSERVICE")
                ).
                route("categorypostgres-service", route -> route.path("/categorypostgres/**")
                        .filters(f -> f.rewritePath("/categorypostgres/?(?<remaining>.*)", "/${remaining}")
                                .circuitBreaker(c->c.setName("categoryCB").setFallbackUri("forward:/categoryfallback"))
                                .requestRateLimiter(c->c.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
                        )
                        .uri("lb://CATEGORYPOSTGRESH")
                ).
                build();
    }


    @Bean
    public KeyResolver userKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getHost().getHostName());
    }


    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(5, 10);
    }




}

