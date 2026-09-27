package com.pocketpilot.controller;

import com.pocketpilot.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;

import static com.pocketpilot.util.SceneNavigator.switchScene;

public class ProfileController {

    @FXML
    private Label usernamelabel;

@FXML
    private Label emaillabel;
@FXML
    private Label userIDlabel;

@FXML
private void initialize(){

    usernamelabel.setText(Session.currentUser.getUsername());
    emaillabel.setText(Session.currentUser.getEmail());
    userIDlabel.setText(Session.currentUser.getUserId());
}


@FXML
private void handlelogout(ActionEvent e) throws IOException {
    Session.currentUser=null;
    switchScene("Login.fxml",e);
}

}
