package com.pocketpilot.controller;

import com.pocketpilot.util.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

import static com.pocketpilot.util.SceneNavigator.switchScene;

public class DashboardController {


    @FXML
    private ImageView imageicon1;

    @FXML
    private ImageView imageicon2;

    @FXML
    private ImageView  imageicon3;

    private Image logoutDefault, logoutGif;



    @FXML
  private BorderPane borderPane;

    @FXML
    private void initialize(){

        logoutDefault = load("/images/logout_default.png"); // match your real file's case
        logoutGif = load("/images/logout.gif");
        imageicon1.setImage(logoutDefault);

    }
    //writing load method
    private Image load(String path) {

        var stream = getClass().getResourceAsStream(path);

        if (stream != null) {
            return new Image(stream);
        }
        else {
            return null;

        }
    }
    @FXML
private void onLogoutHover1(MouseEvent e){

      imageicon1.setImage(logoutGif);
}
@FXML
private void onLogoutExit1(MouseEvent e){

        imageicon1.setImage(logoutDefault);
}



@FXML
private void opensidebar(ActionEvent e) throws IOException {
    Button clicked = (Button) e.getSource();
    String path=clicked.getId()+
".fxml";

    System.out.println("Trying to load: " + path);
    Parent root=loadpage("/"+ path);

     borderPane.setCenter(root);

    System.out.println("Center set. Root children: " + root);
}

private Parent loadpage(String path) throws IOException {
    FXMLLoader loader=new FXMLLoader(getClass().getResource(path));
         return  loader.load();
    }



    @FXML
    private void handleLogout(ActionEvent e) throws IOException {
        Session.currentUser = null;
        switchScene("/Login.fxml", e);
    }




}
