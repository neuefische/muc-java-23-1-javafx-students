package de.neuefische.mucjava231javafxstudents.controller;

import de.neuefische.mucjava231javafxstudents.model.Student;
import de.neuefische.mucjava231javafxstudents.service.SceneSwitchService;
import de.neuefische.mucjava231javafxstudents.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;

public class AllStudentsViewController {

    @FXML
    private ListView<Student> listView;
    @FXML
    private Text text;
    @FXML
    private Button editStudentButton;

    private final StudentService studentService = StudentService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();

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
        sceneSwitchService.switchToWelcomeView(event);
    }

    @FXML
    public void switchToCreateNewStudentView(ActionEvent event) throws IOException {
        sceneSwitchService.switchToCreateNewStudentView(event);
    }

    @FXML
    public void switchToEditSelectedStudentView(ActionEvent event) throws IOException {
        sceneSwitchService.switchToEditSelectedStudentView(event, listView.getSelectionModel().getSelectedItem());
    }
}
