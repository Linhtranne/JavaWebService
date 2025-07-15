package com.data.repository;

import com.data.modal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFullNameContaining(String name);
    List<Student> findByAddressContaining(String address);
    List<Student> findByClassName(String className);
}