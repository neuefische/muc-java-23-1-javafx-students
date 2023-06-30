package de.neuefische.mucjava231javafxstudents.service;

import de.neuefische.mucjava231javafxstudents.model.Student;

public class StudentService {

    public Student saveStudent(String firstName, String lastName, String email, String courseOfStudies) {
        return new Student(firstName, lastName, email, courseOfStudies);
    }
}
