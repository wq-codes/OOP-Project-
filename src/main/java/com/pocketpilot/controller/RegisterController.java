package com.pocketpilot.controller;

import com.pocketpilot.manager.UserManager;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static com.pocketpilot.util.SceneNavigator.switchScene;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


public class RegisterController {

   @FXML
    private TextField username;

   @FXML
    private TextField email;

   @FXML
    private PasswordField  password;

   @FXML
    private PasswordField  confirmpassword;




   private void initialize(){
       username.setOnAction(e-> email.requestFocus());
       email.setOnAction(e->password.requestFocus());
        password.setOnAction(e->confirmpassword.requestFocus());

   }

    @FXML
    private void handlecreateAccount(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (username.getText().trim().isEmpty() || email.getText().trim().isEmpty()
                || password.getText().trim().isEmpty() || confirmpassword.getText().trim().isEmpty()) {

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required!");
            alert.showAndWait();

        } else if (!password.getText().equals(confirmpassword.getText())) {

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match!");
            alert.showAndWait();

        } else {

            UserManager userManager = new UserManager();
            userManager.registeruser(username.getText(), password.getText(), email.getText());
            switchScene("/Login.fxml", event);
        }
    }

    @FXML
private void handlebackarrow(ActionEvent event) throws IOException {

       switchScene("/Login.fxml",event);

}


   }



