package com.pocketpilot.controller;

import com.pocketpilot.manager.UserManager;
import com.pocketpilot.model.User;
import com.pocketpilot.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.pocketpilot.util.SceneNavigator.switchScene;

public class LoginController {

@FXML
  private TextField username;

@FXML
private PasswordField password;

@FXML
      private  void initialize(){

   username.setOnAction(e-> password.requestFocus());
      }



   @FXML
  private void handleSignin(ActionEvent event) throws IOException {

      Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.getDialogPane().setStyle("-fx-background-color: white; -fx-text-fill: black;");

       if (username.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Fields Are Empty!");
          alert.showAndWait();
      } else {
          UserManager userManager = new UserManager();
          User loggedInUser=userManager.login(username.getText(),password.getText());


         if(loggedInUser!= null) {
             Session.currentUser=loggedInUser;
             Stage stage = (Stage) username.getScene().getWindow();

              switchScene("/Dashboard.fxml", event);


             stage.setWidth(1150);
             stage.setHeight(700);


              } else {


              alert.setTitle("Error");
              alert.setHeaderText("Login Error");
              alert.setContentText("Invalid username or Password");
              alert.showAndWait();
          }
      }
  }

    @FXML
    private void openRegister(ActionEvent event ) throws IOException {
       switchScene("/Register.fxml",event);
    }
}
