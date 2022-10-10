package com.example.firstservice;

import javax.ws.rs.GET;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//api gateway를 통과하므로 http://localhost:8081/first-service/** 로 predicate가 붙은 채로 라우팅됨
@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {
    
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to the First service";
    }
    
    @GetMapping("/message")
    public String message(@RequestHeader("first-request")String header){
        log.info(header);
        return "hello world in First Service";
    }

}
