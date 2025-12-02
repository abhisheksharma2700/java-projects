package com.busbookingsystem.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomActuator {
    @ReadOperation
    public String getCustomMessage(){
        return "this is custom endpoint";
    }
}
