package de.neuefische.mucjava231javafxstudents.controller;

import de.neuefische.mucjava231javafxstudents.model.Student;
import de.neuefische.mucjava231javafxstudents.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationViewController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField courseOfStudiesField;
    @FXML
    private CheckBox gdprCheckbox;
    @FXML
    private Label labelErrorMessage;

    private StudentService studentService = StudentService.getInstance();

    public void setStudentDataInFields(Student studentToEdit) {
        firstNameField.setText(studentToEdit.firstName());
        lastNameField.setText(studentToEdit.lastName());
        emailField.setText(studentToEdit.email());
        courseOfStudiesField.setText(studentToEdit.courseOfStudies());
    }

    private boolean isEveryTextFieldValid() {
        if (firstNameField.getText() == null || firstNameField.getText().isEmpty()) {
            labelErrorMessage.setText("Bitte gib einen Vornamen ein");
            return false;
        } else if (lastNameField.getText() == null || lastNameField.getText().isEmpty()) {
            labelErrorMessage.setText("Bitte gib einen Nachnamen ein");
            return false;
        } else if (emailField.getText() == null || emailField.getText().isEmpty()) {
            labelErrorMessage.setText("Bitte gib eine E-Mail-Adresse ein");
            return false;
        } else if (courseOfStudiesField.getText() == null || courseOfStudiesField.getText().isEmpty()) {
            labelErrorMessage.setText("Bitte gib einen Studiengang ein");
            return false;
        } else if (!gdprCheckbox.isSelected()) {
            labelErrorMessage.setText("Bitte akzeptiere die Datenschutzerklärung");
            return false;
        } else {
            labelErrorMessage.setText("");
            return true;
        }
    }

    @FXML
    public void switchToWelcomeView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/welcome-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    public void switchToRegistrationConfirmationView(ActionEvent event) throws IOException {
        if (isEveryTextFieldValid()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/registration-confirmation-view.fxml"));
            Parent root = loader.load();

            RegistrationConfirmationViewController registrationConfirmationViewController = loader.getController();

            Student newStudent = new Student(firstNameField.getText(), lastNameField.getText(), emailField.getText(), courseOfStudiesField.getText());
            studentService.saveStudent(newStudent);
            registrationConfirmationViewController.setSelectedStudent(newStudent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }
    }
}
