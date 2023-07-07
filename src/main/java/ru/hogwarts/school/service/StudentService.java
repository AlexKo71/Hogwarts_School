package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student creatStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long numId) {
        return studentRepository.findById(numId).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }


    public void deleteStudent(long numId) {
        studentRepository.deleteById(numId);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> ageStudents(int age) {
        return studentRepository.findStudentByAge(age);
    }
}
