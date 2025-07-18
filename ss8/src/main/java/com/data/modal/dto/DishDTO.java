package com.data.modal.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DishDTO {
    private String name;
    private String description;
    private Double price;
    private String status;
    private MultipartFile image;
}