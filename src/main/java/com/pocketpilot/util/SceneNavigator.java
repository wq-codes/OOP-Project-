package com.pocketpilot.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
public class SceneNavigator {

    public static void switchScene(String fxmlpath, ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(SceneNavigator.class.getResource(fxmlpath));

        Parent root=loader.load();
        Scene scene =new Scene (root);

        Stage stage=(Stage )((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);

    }

    public static void loadIntoCenter(BorderPane rootPane, String fxmlpath) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlpath));
        Parent content = loader.load();
        rootPane.setCenter(content);
    }






}
