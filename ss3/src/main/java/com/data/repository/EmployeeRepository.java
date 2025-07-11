package com.data.repository;

import com.data.dto.EmployeeDTO;
import com.data.entity.Employee;
import com.data.projection.EmployeeInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        List<Employee> findByPhoneNumber(String phoneNumber);
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") double salary);
    Page<Employee> findAll(Pageable pageable);
    @Query("SELECT new com.data.dto.EmployeeDTO(e.id, e.name, e.email, e.salary, e.phoneNumber) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();
    @Query("SELECT new com.data.dto.EmployeeDTO(e.id, e.name, e.email, e.salary, e.phoneNumber) FROM Employee e WHERE e.salary > :salary")
    List<EmployeeDTO> findEmployeeDTOsBySalaryGreaterThan(@Param("salary") double salary);
    @Query("SELECT e FROM Employee e")
    List<EmployeeInfo> findAllEmployeeInfo();
}
