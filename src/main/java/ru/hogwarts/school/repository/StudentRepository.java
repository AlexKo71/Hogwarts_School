package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByAge(int age);

    Collection<Student> findByAgeIsBetween(int max, int min);

    @Query(value = "SELECT count(*) from student", nativeQuery = true)
    int getNumberOfStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    int getStudentByAgeAverage();

    @Query(value = "select * from student order by  id desc limit 5", nativeQuery = true)
    Collection<Student> fiveLastStudents();

    Collection<Student> findByName(String name);
}
