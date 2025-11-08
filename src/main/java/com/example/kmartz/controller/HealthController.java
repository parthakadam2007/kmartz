package com.example.kmartz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Kmartz API";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

}
