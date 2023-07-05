package de.neuefische.mucjava231javafxstudents;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StudentDBApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        CSSFX.start();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("students/welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);

        scene.setFill(Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.resizableProperty().set(false);
        stage.setTitle("StudentDB-JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}