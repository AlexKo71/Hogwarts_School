package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student creatStudent(@RequestBody Student student) {
        return service.creatStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student student = service.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return service.editStudent(student);
    }

    @DeleteMapping
    public ResponseEntity deleteStudent(@RequestBody long numId) {
       service.deleteStudent(numId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age")
    public Collection<Student> ageStudents(@RequestParam int age) {
        return service.ageStudents(age);
    }

    @GetMapping("/all")
    public Collection<Student> getAllStudent(Student student) {
        return service.getAllStudents();
    }


}
