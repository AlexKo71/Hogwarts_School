package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


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

    public Collection<Student> findByAgeIsBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeIsBetween(minAge, maxAge);
    }

    public FacultyDTO findFaculty(long id) {
        return studentRepository.findById(id).map(student -> {
            FacultyDTO dto = new FacultyDTO();
            dto.setId(student.getFaculty().getId());
            dto.setName(student.getFaculty().getName());
            dto.setColor(student.getFaculty().getColor());
            return dto;
        }).orElse(null);
    }
}
