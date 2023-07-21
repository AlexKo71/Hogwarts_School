package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);

//    @Query(value =
//            "select * from avatar offset ", nativeQuery = true)
//    Avatar findListAvatarStudents(Integer limit, Integer page);

}
