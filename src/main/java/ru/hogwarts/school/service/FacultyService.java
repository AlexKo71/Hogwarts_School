package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;


@Service
public class FacultyService {
    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty creatFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyStudent(long numId) {
        logger.info("Was invoked method for find faculty of student with argument {}", numId);
        return facultyRepository.findById(numId).get();
    }

    public Faculty editStudent(Faculty faculty) {
        logger.info("Was invoked method for edit student of faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteStudent(long numId) {
        logger.info("Was invoked method for delete of student faculty with argument {}", numId);
        facultyRepository.deleteById(numId);
    }

    public Collection<Faculty> getAllFaculty() {
        logger.info("Was invoked method for get all faculty");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> colorFaculty(String color) {
        logger.info("Was invoked method for find faculty by color {}", color);
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for find by name {} or color {} faculty with ignore case", name, color);
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }

    public Collection<StudentDTO> findStudentByFaculty(long id) {
        logger.info("Was invoked method for student by faculty with argument {}", id);
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

    public Optional<String> theLongestName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length));



    }
}
