package de.neuefische.mucjava231javafxstudents.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.mucjava231javafxstudents.model.Student;
import de.neuefische.mucjava231javafxstudents.model.StudentWithoutMatriculationNumber;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.net.http.HttpClient;

public class StudentService {

    private static StudentService instance;
    private final List<Student> students;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String STUDENTS_URL_BACKEND = "http://localhost:8080/api/students";

    private StudentService() {
        students = new ArrayList<>();
        students.addAll(Arrays.asList(
                new Student("1", "Max", "Mustermann", "max@jahoo.de", "Sport"),
                new Student("2", "Erika", "Mustermann", "erika@gmail.com", "Kunst"),
                new Student("3", "Willi", "Wichtig", "wiktiigg123@spammail.de", "Cybersecurity")
        ));
    }

    // Singleton -> es gibt nur eine Instanz von StudentService
    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public Student createNewStudent(StudentWithoutMatriculationNumber student) {
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

    public Student updateStudent(Student student) {
        students.removeIf(studentFromList -> studentFromList.matriculationNumber().equals(student.matriculationNumber()));
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(STUDENTS_URL_BACKEND))
                .header("Accept", "application/json")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(responseBody -> mapToObjectList(responseBody))
                .join();
    }

    private Student mapToObject(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, Student.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Student> mapToObjectList(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
