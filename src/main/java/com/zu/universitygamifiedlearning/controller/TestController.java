package com.zu.universitygamifiedlearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, secure world!";
    }
    @GetMapping("/admin")
    public String admin() {
        return "Hello, admin user!";
    }
}
