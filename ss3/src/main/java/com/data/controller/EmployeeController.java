package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.data.dto.EmployeeDTO;
import com.data.service.EmployeeService;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        try {
            EmployeeDTO dto = employeeService.getEmployeeById(id);
            model.addAttribute("employee", dto);
            return "employee-detail";
        } catch (RuntimeException e) {
            return "error";
        }
    }

    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO(null, "", "",  0.0,""));
        return "employee-add";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute EmployeeDTO dto) {
        employeeService.createEmployee(dto);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String updateEmployeeForm(@PathVariable Long id, Model model) {
        try {
            EmployeeDTO dto = employeeService.getEmployeeById(id);
            model.addAttribute("employeeDTO", dto);
            return "employee-edit";
        } catch (RuntimeException e) {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute EmployeeDTO dto) {
        try {
            employeeService.updateEmployee(id, dto);
            return "redirect:/employees";
        } catch (RuntimeException e) {
            return "error";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return "redirect:/employees";
        } catch (RuntimeException e) {
            return "error";
        }
    }
}