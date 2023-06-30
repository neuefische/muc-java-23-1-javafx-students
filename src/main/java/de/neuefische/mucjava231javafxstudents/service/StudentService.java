package de.neuefische.mucjava231javafxstudents.service;

import de.neuefische.mucjava231javafxstudents.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentService {

    private static StudentService instance;
    private List<Student> students;

    private StudentService() {
        students = new ArrayList<>();
        students.addAll(Arrays.asList(
                new Student("Max", "Mustermann", "max@jahoo.de", "Sport"),
                new Student("Erika", "Mustermann", "erika@gmail.com", "Kunst"),
                new Student("Willi", "Wichtig", "wiktiigg123@spammail.de", "Cybersecurity")
        ));
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void saveStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
