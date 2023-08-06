package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findByColor(String color);

    Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color);

    @Query("select f from Faculty f join Student s on s.faculty.id = f.id where s.id = :studentId")
    Faculty findFacultyByStudentId(long studentId);

}
