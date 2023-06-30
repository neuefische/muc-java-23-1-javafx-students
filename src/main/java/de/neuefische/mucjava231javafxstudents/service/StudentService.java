package de.neuefische.mucjava231javafxstudents.service;

import de.neuefische.mucjava231javafxstudents.model.Student;
import de.neuefische.mucjava231javafxstudents.model.StudentWithoutId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StudentService {

    private static StudentService instance;
    private List<Student> students;

    private StudentService() {
        students = new ArrayList<>();
        students.addAll(Arrays.asList(
                new Student("1", "Max", "Mustermann", "max@jahoo.de", "Sport"),
                new Student("2", "Erika", "Mustermann", "erika@gmail.com", "Kunst"),
                new Student("3", "Willi", "Wichtig", "wiktiigg123@spammail.de", "Cybersecurity")
        ));
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public Student createNewStudent(StudentWithoutId student) {
        Student studentWithId = new Student(
                UUID.randomUUID().toString(),
                student.firstName(),
                student.lastName(),
                student.email(),
                student.courseOfStudies()
        );

        students.add(studentWithId);
        return studentWithId;
    }

    public void updateStudent(Student student) {
        students.removeIf(studentFromList -> studentFromList.id().equals(student.id()));
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
