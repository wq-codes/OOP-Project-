package com.pocketpilot.controller;

import com.pocketpilot.manager.IncomeManager;
import com.pocketpilot.model.Income;
import com.pocketpilot.util.Session;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class IncomeController {

@FXML
private Label totallabel;
@FXML
  private TextField amount;

@FXML
    private TextField source;

@FXML
    private TextField note;
@FXML
    private DatePicker date;
@FXML
private TableView<Income> incometable;

    @FXML private TableColumn<Income, String> sourcecolumn;
    @FXML private TableColumn<Income, Double> amountcolumn;
    @FXML private TableColumn<Income, LocalDate> datecolumn;
    @FXML private TableColumn<Income, String> notecolumn;

    IncomeManager incomeManager=new IncomeManager();

//initilaize method
@FXML
private void initialize(){
    amount.setOnAction(e-> source .requestFocus());
  source.setOnAction(e->  note.requestFocus());


  //telling the column from where to fetch the value
  sourcecolumn.setCellValueFactory(
          new PropertyValueFactory<>("source")
  );
amountcolumn.setCellValueFactory(
        new PropertyValueFactory<>("amount")
);
     datecolumn.setCellValueFactory(
             new PropertyValueFactory<>("date")
     );
     notecolumn.setCellValueFactory(
             new PropertyValueFactory<>("description")
     );


    // loading the previous data of the user for the column
    incometable.setItems(FXCollections.observableArrayList(
            incomeManager.getincomeforUsers(Session.currentUser.getUserId())
    ));
    totallabel.setText("Total Income: " + incomeManager.gettotalincome(Session.currentUser.getUserId()) + " PKR");

}

@FXML
     private  void handleaddincome(ActionEvent e) throws IOException {

   Alert alert=new Alert(Alert.AlertType.ERROR);


    if (amount.getText().trim().isEmpty() || date.getValue() ==null ||
            note.getText().trim().isEmpty()|| source.getText().trim().isEmpty()
    )       {

          alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("All Fields are Required!");
        alert.showAndWait();
    }

    else {
  try {
      incomeManager.addincome(Double.parseDouble(amount.getText()), date.getValue()
              , note.getText(), source.getText(), Session.currentUser.getUserId());

      //loading the recent or latest data of the user after cilicking add income by user
    incometable.setItems(
            FXCollections.observableArrayList(
                    incomeManager.getincomeforUsers(
                            Session.currentUser.getUserId()
                    ) )
    );
   //editing the totalinocme
   totallabel.setText("Total income:"+
           incomeManager.gettotalincome(Session.currentUser.getUserId()));


   //this will clear the text field so if another entry had to be added so no need to clear it manually by user
      amount.clear();
      source.clear();
      note.clear();
      date.setValue(null);


  }
   catch (NumberFormatException ex){
     alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Amount must be valid Number");
      alert.showAndWait();
   }
    }

}


}
