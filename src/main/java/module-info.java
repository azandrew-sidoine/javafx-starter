module dev.azlabs.modernclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens dev.azlabs.modernclient to javafx.fxml;
    exports dev.azlabs.modernclient;
}