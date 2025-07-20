package com.data.component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Logger {

    public void logMessages() {
        log.trace("Đây là log TRACE");
        log.debug("Đây là log DEBUG");
        log.info("Đây là log INFO");
        log.warn("Đây là log WARN");
        log.error("Đây là log ERROR");
    }
}