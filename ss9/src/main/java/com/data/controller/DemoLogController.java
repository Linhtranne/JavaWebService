package com.data.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo-log")
public class DemoLogController {

    @RequestMapping("/trace")
    public String logTrace() {
        log.trace("Đã ghi log trace");
        return "TRACE log ghi thành công!";
    }

    @RequestMapping("/debug")
    public String logDebug() {
        log.debug("Đã ghi log debug");
        return "DEBUG log ghi thành công!";
    }

    @RequestMapping("/info")
    public String logInfo() {
        log.info("Đã ghi log info");
        return "INFO log ghi thành công!";
    }

    @RequestMapping("/warning")
    public String logWarning() {
        log.warn("Đã ghi log warning");
        return "WARN log ghi thành công!";
    }

    @RequestMapping("/error")
    public String logError() {
        log.error("Đã ghi log error");
        return "ERROR log ghi thành công!";
    }
}