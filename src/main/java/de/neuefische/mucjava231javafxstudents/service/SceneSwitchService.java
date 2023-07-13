package de.neuefische.mucjava231javafxstudents.service;

import de.neuefische.mucjava231javafxstudents.controller.RegistrationConfirmationViewController;
import de.neuefische.mucjava231javafxstudents.controller.UpdateViewController;
import de.neuefische.mucjava231javafxstudents.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class SceneSwitchService {

    private static SceneSwitchService instance;

    public static synchronized SceneSwitchService getInstance() {
        if (instance == null) {
            instance = new SceneSwitchService();
        }
        return instance;
    }

    public void switchToWelcomeView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/fxml/welcome-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void switchToCreateNewStudentView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/fxml/registration-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void switchToShowRegisteredStudentView(ActionEvent event, Student updatedStudent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/fxml/registration-confirmation-view.fxml"));
        Parent root = loader.load();

        RegistrationConfirmationViewController registrationConfirmationViewController = loader.getController();
        registrationConfirmationViewController.setSelectedStudent(updatedStudent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void switchToEditSelectedStudentView(ActionEvent event, Student selectedStudent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/fxml/update-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        UpdateViewController updateViewController = loader.getController();
        updateViewController.setStudentDataInFields(selectedStudent);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void switchToListAllStudentsView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/fxml/all-students-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setScene(scene);

        stage.show();
    }
}
