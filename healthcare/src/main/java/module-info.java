module org.example.healthcare {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.healthcare to javafx.fxml;
    exports org.example.healthcare;
}