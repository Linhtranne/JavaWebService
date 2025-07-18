package com.data.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Boolean status;
    private String message;
    private T data;

}