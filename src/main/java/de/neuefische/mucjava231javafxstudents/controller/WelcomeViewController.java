package de.neuefische.mucjava231javafxstudents.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class WelcomeViewController {

    @FXML
    public void switchToRegistrationScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/neuefische/mucjava231javafxstudents/students/registration-view.fxml"));
        Parent root = loader.load();

        // Wir holen uns die Stage indem wir
        // aus dem Event -> Das Auslösende Element (Button) -> Die Scene -> Die Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}