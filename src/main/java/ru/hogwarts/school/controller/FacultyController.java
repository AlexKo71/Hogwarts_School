package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.creatFaculty(faculty);
    }


    @GetMapping("{id}")
    public ResponseEntity<Faculty> findStudent(@PathVariable long id) {
        Faculty student = facultyService.findFacultyStudent(id);
        if (student == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public Faculty editStudent(@RequestBody Faculty faculty) {
        return facultyService.editStudent(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteStudent(@PathVariable long id) {
        facultyService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color")
    public Collection<Faculty> colorFaculty(@RequestParam String color) {
        return facultyService.colorFaculty(color);
    }

    @GetMapping("/all")
    public Collection<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @GetMapping("findFaculty")
    public Collection<Faculty> findByNameOrColorIgnoreCase(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {
        return facultyService.findByNameOrColorIgnoreCase(name, color);
    }

    @GetMapping("/{id}/students")
    public Collection<StudentDTO> getStudents(@PathVariable long id) {
        return facultyService.findStudentByFaculty(id);
    }
}
