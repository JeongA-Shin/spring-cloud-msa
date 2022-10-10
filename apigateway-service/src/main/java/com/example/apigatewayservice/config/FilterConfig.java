package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
    
    //@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
            //first-service 등록
            .route(r->r.path("/first-service/**") //이 path로 요청이 들어오면
                .filters(f->f.addRequestHeader("first-request","first-request-header")  //요청시엔 헤더에 이렇게 넣어줌 - 마이크로 서비스가 받는 요청의 헤더엔 이 정보가 들어가 있음
                    .addResponseHeader("first-response","first-response-header") // 마이크로 서비스로부터 응답을 받을 땐 이 헤더를 넣어줌 - 클라이언트가 받은 response엔 이 헤더가 있는 것
                ) //이 필터를 거친 다음에
                .uri("http://localhost:8081")) //이 uri로 요청하겠다
            //second-service 등록
            .route(r->r.path("/second-service/**") //이 path로 요청이 들어오면
                .filters(f->f.addRequestHeader("second-request","second-request-header")  //요청시엔 헤더에 이렇게 넣어줌 - 마이크로 서비스가 받는 요청의 헤더엔 이 정보가 들어가 있음
                    .addResponseHeader("second-response","second-response-header") // 마이크로 서비스로부터 응답을 받을 땐 이 헤더를 넣어줌 - 클라이언트가 받은 response엔 이 헤더가 있는 것
                ) //이 필터를 거친 다음에
                .uri("http://localhost:8082")) //이 uri로 요청하겠다
            .build();
    
    }

}
