package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//api gateway를 통과하므로 http://localhost:8081/second-service/** 로 predicate가 붙은 채로 라우팅됨
@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to the Second serivce";
    }
    
    @GetMapping("/message")
    public String message(@RequestHeader("second-request")String header){
        log.info(header);
        return "hello world in Second Service";
    }
}
