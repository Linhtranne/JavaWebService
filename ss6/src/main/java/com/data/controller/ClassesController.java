package com.data.controller;

import com.data.entity.Classes;
import com.data.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    private final ClassesService classesService;

    @Autowired
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping
    public ResponseEntity<List<Classes>> getAllClasses() {
        List<Classes> classes = classesService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> getClassById(@PathVariable Long id) {
        Classes classes = classesService.getClassById(id);
        return classes != null ? new ResponseEntity<>(classes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Classes> createClass(@RequestBody Classes classes) {
        Classes createdClass = classesService.createClass(classes);
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classes> updateClass(@PathVariable Long id, @RequestBody Classes classes) {
        Classes updatedClass = classesService.updateClass(id, classes);
        return updatedClass != null ? new ResponseEntity<>(updatedClass, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}