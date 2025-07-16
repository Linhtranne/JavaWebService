package com.data.service;

import com.data.entity.Students;
import com.data.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Students getStudentById(Long id) {
        return studentsRepository.findById(id).orElse(null);
    }

    public Students createStudent(Students student) {
        return studentsRepository.save(student);
    }

    public Students updateStudent(Long id, Students updatedStudent) {
        return studentsRepository.findById(id)
                .map(existingStudent -> {
                    updatedStudent.setStuId(id);
                    return studentsRepository.save(updatedStudent);
                })
                .orElse(null);
    }

    public void deleteStudent(Long id) {
        studentsRepository.deleteById(id);
    }
}
