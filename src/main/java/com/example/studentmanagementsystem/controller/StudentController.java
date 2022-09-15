package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring Boot";
    }

    @GetMapping("/hello")
    public String hello(){
        return "<h1>Hello From Dharmendra</h1>";
    }

    @GetMapping("/listStudents")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentRepository.findById(id).get();
    }

    @PostMapping("/student")
    public String addStudent(@RequestBody Student student){
        boolean studentExist = studentRepository.existsById(student.getId());

        if(!studentExist){
            studentRepository.save(student);
            return "Record saved successfully";
        }
        else{
            return "Student already exists!!!!";
        }
    }

    @PutMapping("/student/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable Integer id){
        Student studentObj = studentRepository.findById(id).get();
        studentObj.setFirstName(student.getFirstName());
        studentObj.setLastName(student.getLastName());
        studentObj.setEmail(student.getEmail());

        studentRepository.save(studentObj);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentRepository.delete(studentRepository.findById(id).get());
    }
}
