package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component // 빈으로 등록시킴
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    
    public static class Config{
        //Put the configuration properties
        //딱히 지금은 설정정보로 뭘 넣을 게 없으므로 걍 빈 것으로 둠
    }
    
    //생성자
    public CustomFilter(){
        super(Config.class);
    }
    
    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre filter
        
        return (exchange,chain)->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            
            log.info("Custom PRE filter: requestId ->{}",request.getId());
            
            //Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("Custom Post filter: response status code ->{}",response.getStatusCode());
            }));
        };
    }
    
    
}
