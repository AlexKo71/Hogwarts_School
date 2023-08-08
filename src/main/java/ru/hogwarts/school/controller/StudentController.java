package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

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

    @GetMapping("/{id}")
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

    @GetMapping("/findByAgeIsBetween")
    public Collection<Student> findByAgeIsBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return service.findByAgeIsBetween(minAge, maxAge);
    }

    @GetMapping("/all")
    public Collection<Student> getAllStudents(Student student) {
        return service.getAllStudents();
    }


    @GetMapping("/{id}/faculty")
    public FacultyDTO findFaculty(@PathVariable long id) {
        return service.findFaculty(id);
    }

    @GetMapping("/getNumberOfStudents")
    public int getNumberOfStudents() {
        return service.getNumberOfStudents();
    }

    @GetMapping("/getStudentByAgeAverage")
    public int getStudentByAgeAverage() {
        return service.getStudentByAgeAverage();
    }

    @GetMapping("/findSeveralStudents")
    public List<Student> findSeveralStudents(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        return service.findSeveralStudents(pageNumber, pageSize);
    }

    @GetMapping("/fiveLastStudents")
    public Collection<Student> fiveLastStudents() {
        return service.fiveLastStudents();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Collection<Student>> findByName(@PathVariable String name) {
        Collection<Student> students = service.findByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/findStudentsByNameH")
    public Collection<String> findStudentsByNameA() {
        return service.findStudentsByNameH();
    }

    @GetMapping("/findAverageStudents")
    public double findAverageStudents() {
        return service.findAverageStudents();
    }

    @GetMapping("/listStudentsPrintln")
    public void listStudentsPrintln(){
      service.listStudentsPrintln();
    }

    @GetMapping("/listStudentsSynchronized")
    public void studentsSynchronizedPrintln() {
        service.listStudentsSynchronized();
    }
}
