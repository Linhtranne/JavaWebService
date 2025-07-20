package com.data.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Ex5Controller {

    @GetMapping("/error")
    public String throwError() {
        throw new RuntimeException("Đã xảy ra lỗi");
    }
}