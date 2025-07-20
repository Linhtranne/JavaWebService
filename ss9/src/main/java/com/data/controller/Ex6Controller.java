package com.data.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class Ex6Controller {

    @GetMapping("/log")
    public String logEvent() {
        log.info("\n" +
                "Đây là một sự kiện nhật ký thông tin");
        log.warn("Đây là một cảnh báo");
        log.error("Đây là sự kiện lỗi");
        return "\n" +
                "JSON đã được ghi lại!\n";
    }
}