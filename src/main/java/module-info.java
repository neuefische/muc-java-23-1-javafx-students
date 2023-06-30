module de.neuefische.mucjava231javafxstudents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens de.neuefische.mucjava231javafxstudents to javafx.fxml;
    exports de.neuefische.mucjava231javafxstudents;
}