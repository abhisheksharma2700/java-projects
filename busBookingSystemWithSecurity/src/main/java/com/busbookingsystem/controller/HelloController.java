package com.busbookingsystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Tag(name = "HelloController")
public class HelloController {
    @Value("${welcome.message}")
    private String welcomeMessage;
    @GetMapping("/")
    public String api(){
        return "openapi";
    }

    @GetMapping("/welcome")
    public String helloWorld(){
        return welcomeMessage;
    }
}
