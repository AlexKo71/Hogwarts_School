package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {
    public final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student creatStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long numId) {
        logger.info("Was invoked method find student with argument {}", numId);
        return studentRepository.findById(numId).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }


    public void deleteStudent(long numId) {
        logger.info("Was invoked method delete student with argument {}", numId);
        studentRepository.deleteById(numId);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> ageStudents(int age) {
        logger.info("Was invoked method for get all students aged {}", age);
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findByAgeIsBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for get all students between the ages of {} to {}", minAge, maxAge);
        return studentRepository.findByAgeIsBetween(minAge, maxAge);
    }

    public FacultyDTO findFaculty(long id) {
        logger.info("Was invoked method to find the faculty of the student by argument {}", id);
        return studentRepository.findById(id).map(student -> {
            FacultyDTO dto = new FacultyDTO();
            dto.setId(student.getFaculty().getId());
            dto.setName(student.getFaculty().getName());
            dto.setColor(student.getFaculty().getColor());
            return dto;
        }).orElse(null);
    }

    public int getNumberOfStudents() {
        logger.info("Was invoked method for get number of students");
        return studentRepository.getNumberOfStudents();
    }

    public int getStudentByAgeAverage() {
        logger.info("Was invoked method for get age average of student");
        return studentRepository.getStudentByAgeAverage();
    }

    public List<Student> findSeveralStudents(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for several students by size {} and page number {}", pageSize, pageNumber);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }

    public Collection<Student> fiveLastStudents() {
        logger.info("Was invoked method for find five last students");
        return studentRepository.fiveLastStudents();
    }

    public Collection<Student> findByName(String name) {
        logger.info("Was invoked method for find student by name {}", name);
        return studentRepository.findByName(name);
    }

    public List<String> findStudentsByNameH() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("H"))
                .sorted()
                .collect(Collectors.toList());


    }

    public double findAverageStudents() {
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .summaryStatistics().getAverage();

    }

    public void listStudentsPrintln() {
        var students = studentRepository.findAll().stream().limit(6).collect(Collectors.toList());
        students.forEach(System.out::println);
        System.out.println("====students====");

        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();

    }

    public void listStudentsSynchronized() {
        var students = studentRepository.findAll().stream().limit(6).collect(Collectors.toList());
        System.out.println("==============");

        orderList(String.valueOf(students.get(0)));
        orderList(String.valueOf(students.get(1)));

        new Thread(() -> {
            orderList(String.valueOf(students.get(2)));
            orderList(String.valueOf(students.get(3)));
        }).start();

        new Thread(() -> {
            orderList(String.valueOf(students.get(4)));
            orderList(String.valueOf(students.get(5)));
        }).start();

    }

    private synchronized void orderList(String name) {
        System.out.println(name);
    }

}
