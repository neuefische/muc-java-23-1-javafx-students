package de.neuefische.mucjava231javafxstudents.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.mucjava231javafxstudents.model.Student;
import de.neuefische.mucjava231javafxstudents.model.StudentWithoutMatriculationNumber;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.net.http.HttpClient;

public class StudentService {

    private static StudentService instance;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String STUDENTS_URL_BACKEND = "http://localhost:8080/api/students";

    private StudentService() {}

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

        return studentWithId;
    }

    public Student updateStudent(Student student) {
        try {
            String requestBody = objectMapper.writeValueAsString(student);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(STUDENTS_URL_BACKEND + "/" + student.matriculationNumber()))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(responseBody -> mapToStudent(responseBody))
                    .join();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(STUDENTS_URL_BACKEND))
                .header("Accept", "application/json")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(responseBody -> mapToStudentList(responseBody))
                .join();
    }

    private Student mapToStudent(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, Student.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Student> mapToStudentList(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
