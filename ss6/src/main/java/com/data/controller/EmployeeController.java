package com.data.controller;
import com.data.entity.Employee;
import com.data.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Api(value = "API Nhân viên", description = "Các thao tác quản lý nhân viên")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "Lấy danh sách tất cả nhân viên", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lấy danh sách nhân viên thành công")
    })
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lấy thông tin nhân viên theo ID", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lấy thông tin nhân viên thành công"),
            @ApiResponse(code = 404, message = "Không tìm thấy nhân viên")
    })
    public ResponseEntity<Employee> getEmployeeById(
            @ApiParam(value = "ID của nhân viên cần lấy", required = true) @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value = "Tạo mới một nhân viên")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tạo nhân viên thành công"),
            @ApiResponse(code = 400, message = "Dữ liệu đầu vào không hợp lệ")
    })
    public ResponseEntity<Employee> createEmployee(
            @ApiParam(value = "Đối tượng nhân viên cần tạo", required = true) @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Cập nhật thông tin nhân viên")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cập nhật nhân viên thành công"),
            @ApiResponse(code = 404, message = "Không tìm thấy nhân viên")
    })
    public ResponseEntity<Employee> updateEmployee(
            @ApiParam(value = "ID của nhân viên cần cập nhật", required = true) @PathVariable Long id,
            @ApiParam(value = "Đối tượng nhân viên sau khi cập nhật", required = true) @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Xóa một nhân viên")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Xóa nhân viên thành công"),
            @ApiResponse(code = 404, message = "Không tìm thấy nhân viên")
    })
    public ResponseEntity<Void> deleteEmployee(
            @ApiParam(value = "ID của nhân viên cần xóa", required = true) @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}