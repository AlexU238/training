package com.u238.training.rest;


import com.u238.training.entity.Student;
import com.u238.training.errorResponse.StudentErrorResponse;
import com.u238.training.errorResponse.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @GetMapping("/hello")
    String sayHello() {
        return "Hello world";
    }

    List<Student> students;

    @PostConstruct
    private void populate() {
        students = new ArrayList<>();
        students.add(new Student("Boris", "Hanson"));
        students.add(new Student("Mario", "Rossi"));
        students.add(new Student("Mary", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return students;
    }


    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student not found - " + studentId);
        }
        return students.get(studentId);
    }


}
