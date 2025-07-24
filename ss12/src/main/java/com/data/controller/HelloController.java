package com.data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Đây là endpoint công khai.";
    }

    @GetMapping("/private/hello")
    public String privateHello() {
        return "Đây là endpoint bảo vệ.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Đây là endpoint bảo vệ.";
    }
}
