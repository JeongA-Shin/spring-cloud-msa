package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component // 빈으로 등록시킴
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    
    @Data
    public static class Config{
        //Put the configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
    
    //생성자
    public GlobalFilter(){
        super(Config.class);
    }
    
    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre filter
        
        return (exchange,chain)->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            
            log.info("Global Filter baseMessage: {}",config.getBaseMessage());
            
            //preFilter logger가 작동한다면
            if(config.isPreLogger()){
                log.info("Pre Filter Start: request id -> {}",request.getId());
            }
            
            //Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                //preFilter logger가 작동한다면
                if(config.isPostLogger()){
                    log.info("Global Post Filter End: response code -> {}",response.getStatusCode());
                }
            }));
        };
    }
    
    
}
