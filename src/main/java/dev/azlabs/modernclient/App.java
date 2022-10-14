package dev.azlabs.modernclient;

import dev.azlabs.modernclient.core.RootInjector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws IOException {
        var loader = new FXMLLoader(App.class.getResource("view.fxml"));
        // Custom Controller initialization
        var controller = RootInjector.make(ViewController.class);
        if (controller != null) {
            loader.setController(controller);
        }
        // Returns the current controller loaded by the FXML view builder
        // loader.getController();
        Scene scene = new Scene(loader.load(), 350, 240);
        s.setScene(scene);
        s.setTitle("Modern Application");
        s.show();
    }
}
