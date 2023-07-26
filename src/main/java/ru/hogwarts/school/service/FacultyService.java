package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty creatFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyStudent(long numId) {
        return facultyRepository.findById(numId).get();
    }

    public Faculty editStudent(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteStudent(long numId) {
        facultyRepository.deleteById(numId);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> colorFaculty(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color) {
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }

    public Collection<StudentDTO> findStudentByFaculty(long id) {
        return facultyRepository.findById(id)
                .map(f -> {
                    var studentDtos = new ArrayList<StudentDTO>();
                    for (Student student : f.getStudents()) {
                        var facDto = new FacultyDTO(student.getFaculty().getId(), student.getFaculty().getName(), student.getFaculty().getColor());
                        var dto = new StudentDTO(student.getId(), student.getName(), student.getAge(), facDto);
                        studentDtos.add(dto);
                    }
                    return studentDtos;
                })
                .orElse(new ArrayList<>());
    }
}
