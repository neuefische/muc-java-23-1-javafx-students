package de.neuefische.mucjava231javafxstudents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//import org.kordamp.bootstrapfx.BootstrapFX;

public class StudentDBApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("students/fxml/welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Application.setUserAgentStylesheet(Objects.requireNonNull(getClass().getResource("students/css/GlobalStyles.css")).toExternalForm());

        stage.resizableProperty().set(false);
        stage.setTitle("StudentDB-JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}