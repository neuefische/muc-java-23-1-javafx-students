package de.neuefische.mucjava231javafxstudents.controller;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Arrays;

public class AllStudentsViewController {

    // Wir benutzen die ListView vom Typ <String>
    @FXML
    private ListView<String> listView;
    @FXML
    private Button addButton;
    @FXML
    private TextField textField;
    @FXML
    private Text text;

    public void initialize() {
        // Hier stellen wir die Daten der ListView ein
        //  listView.getItems() = Liste der Elemente aus listView holen
        // .addAll() = Elemente zur Liste hinzufügen
        listView.getItems().addAll(Arrays.asList("Paramore", "Sum 41", "Green Day", "Linkin Park"));

        listView.getSelectionModel()
                .selectedItemProperty()
                // Listener der etwas macht
                .addListener(
                        (observableValue, s, t1) -> {
                            System.out.println(listView.getSelectionModel().getSelectedItem());
                            text.setText(listView.getSelectionModel().getSelectedItem());
                        }
                );

        textField.addEventHandler(EventType.ROOT, event -> {
            addButton.setDisable(textField.getText().isEmpty());
        });
    }

    @FXML
    void addItemToList() {
        listView.getItems().add(textField.getText());
    }
}
