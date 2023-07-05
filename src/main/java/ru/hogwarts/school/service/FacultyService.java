package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;


import java.util.Collection;
import java.util.stream.Collectors;

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
        return facultyRepository.findAll().stream().
                filter(p -> (p.getColor().equals(color))).
                collect(Collectors.toList());
    }
}
