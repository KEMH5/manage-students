package com.training.integratePostgres;

import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {


    @GetMapping("/hello")
    public String SayHello(){
        return "Hello from my first controller";
    }


    @PostMapping("/post")
    public String post(
            @RequestBody
            String msg
    ){
        return "Request accepted and message is : " + msg;
    }

}




