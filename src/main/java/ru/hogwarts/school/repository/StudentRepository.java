package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByAge(int age);

    Collection<Student> findByAgeIsBetween(int max, int min);

    Collection<Student> findAllByFaculty_Id(long id);

}
