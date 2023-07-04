package de.neuefische.mucjava231javafxstudents.controller;

import de.neuefische.mucjava231javafxstudents.model.Student;
import de.neuefische.mucjava231javafxstudents.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AllStudentsViewController {

    @FXML
    private ListView<Student> listView;
    @FXML
    private Text text;
    @FXML
    private Button editStudentButton;

    private StudentService studentService = StudentService.getInstance();

    public void initialize() {
        // Hier stellen wir die Daten der ListView ein
        //  listView.getItems() = Liste der Elemente aus listView holen
        // .addAll() = Elemente zur Liste hinzufügen
        listView.getItems().addAll(studentService.getAllStudents());

        listView.getSelectionModel()
                .selectedItemProperty()
                // Listener der etwas macht
                .addListener(
                        (observableValue, s, t1) -> {
                            text.setText(listView.getSelectionModel().getSelectedItem().firstName() + " " + listView.getSelectionModel().getSelectedItem().lastName());
                            editStudentButton.setDisable(false);
                        }
                );
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
    public void switchToEditSelectedStudentView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/update-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        UpdateViewController updateViewController = loader.getController();
        updateViewController.setStudentDataInFields(listView.getSelectionModel().getSelectedItem());

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    public void switchToCreateNewStudentView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/registration-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
