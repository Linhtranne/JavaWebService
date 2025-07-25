package com.data.execption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Đã xảy ra lỗi: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("\n" +
                "Đã xảy ra lỗi. Vui lòng kiểm tra nhật ký để biết chi tiết.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}