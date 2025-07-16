package com.data.service;

import com.data.entity.Classes;
import com.data.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService {

    private final ClassesRepository classesRepository;

    @Autowired
    public ClassesService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    public Classes getClassById(Long id) {
        return classesRepository.findById(id).orElse(null);
    }

    public Classes createClass(Classes classes) {
        return classesRepository.save(classes);
    }

    public Classes updateClass(Long id, Classes updatedClass) {
        return classesRepository.findById(id)
                .map(existingClass -> {
                    updatedClass.setClassId(id);
                    return classesRepository.save(updatedClass);
                })
                .orElse(null);
    }

    public void deleteClass(Long id) {
        classesRepository.deleteById(id);
    }
}