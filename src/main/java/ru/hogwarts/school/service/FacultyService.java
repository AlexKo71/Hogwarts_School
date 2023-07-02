package ru.hogwarts.school.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long numId = 0;

    public Faculty creatFaculty(Faculty faculty) {
        faculty.setId(++numId);
        faculties.put(numId, faculty);
        return faculty;
    }

    public Faculty findFacultyStudent(long numId) {
        return faculties.get(numId);
    }

    public ResponseEntity editStudent(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            return ResponseEntity.ok(faculties.put(faculty.getId(), faculty));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<Faculty> deleteStudent(long numId) {
        if (faculties.containsKey(numId)) {
            return ResponseEntity.ok(faculties.remove(numId));
        }
        return ResponseEntity.notFound().build();
    }

    public Collection<Faculty> getAllFaculty() {
        return faculties.values();
    }

    public Collection<Faculty> colorFaculty(String color) {
        return faculties.values().stream().
                filter(p -> (p.getColor().equals(color))).
                collect(Collectors.toList());
    }
}
