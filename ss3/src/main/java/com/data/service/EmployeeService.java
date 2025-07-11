package com.data.service;

import com.data.dto.EmployeeDTO;
import com.data.entity.Employee;
import com.data.projection.EmployeeInfo;
import com.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployeeDTOs();
    }
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee e = optional.get();
            return new EmployeeDTO(e.getId(), e.getName(), e.getEmail(), e.getSalary(), e.getPhoneNumber());
        }
        throw new RuntimeException("Employee not found");
    }
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee e = new Employee();
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setSalary(dto.getSalary());
        e.setPhoneNumber("Unknown");
        employeeRepository.save(e);
        return new EmployeeDTO(e.getId(), e.getName(), e.getEmail(), e.getSalary(), e.getPhoneNumber());
    }
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setSalary(dto.getSalary());
        employeeRepository.save(e);
        return new EmployeeDTO(e.getId(), e.getName(), e.getEmail(), e.getSalary(), e.getPhoneNumber());
    }
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public List<EmployeeDTO> findEmployeesBySalaryGreaterThan(double salary) {
        return employeeRepository.findEmployeeDTOsBySalaryGreaterThan(salary);
    }
    public List<EmployeeInfo> getAllEmployeeInfo() {
        return employeeRepository.findAllEmployeeInfo();
    }
    public Page<Employee> getEmployeesWithPagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return employeeRepository.findAll(pageable);
    }
}
