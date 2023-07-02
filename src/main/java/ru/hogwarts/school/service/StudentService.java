package ru.hogwarts.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long numId = 0;

    public Student creatStudent(Student student) {
        student.setId(++numId);
        students.put(numId, student);
        return student;
    }

    public Student findStudent(long numId) {
        return students.get(numId);
    }

    public ResponseEntity<Student> editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return ResponseEntity.ok(students.put(student.getId(), student));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    public ResponseEntity<Student> deleteStudent(long numId) {
        if (students.containsKey(numId)) {
            return ResponseEntity.ok(students.remove(numId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Student> ageStudents(int age) {
        return students.values().stream().
                filter(p -> (p.getAge() == age)).
                collect(Collectors.toList());
    }
}
