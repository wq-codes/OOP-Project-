package com.pocketpilot.main;


import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        stage.setTitle("Pocket Pilot");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/pocketpilot.png")));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
