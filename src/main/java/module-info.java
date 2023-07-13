module de.neuefische.mucjava231javafxstudents {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.neuefische.mucjava231javafxstudents to javafx.fxml;
    exports de.neuefische.mucjava231javafxstudents;
    exports de.neuefische.mucjava231javafxstudents.controller;
    opens de.neuefische.mucjava231javafxstudents.controller to javafx.fxml;
}