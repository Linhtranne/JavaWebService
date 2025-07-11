package com.data.dto;

import lombok.Getter;

@Getter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private double salary;
    private String phoneNumber;

    public EmployeeDTO(Long id, String name, String email, double salary, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

}
