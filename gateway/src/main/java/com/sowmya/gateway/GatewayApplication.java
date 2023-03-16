package com.sowmya.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  RouteLocator getRoute(RouteLocatorBuilder routeBuilder) {
    return routeBuilder.routes()
        .route("carDetailsRoute",
            routeSpec -> routeSpec.path("/carDetails/**").uri("lb://CAR-DETAILS-SERVICE"))
        .route("carLeaseDetailsRoute",
            routeSpec -> routeSpec.path("/carLease/**").uri("lb://CAR-DETAILS-SERVICE"))
        .route("customerServiceRoute",
            routeSpec -> routeSpec.path("/customerDetails/**").uri("lb://CUSTOMER-SERVICE"))
        .build();
  }
}
