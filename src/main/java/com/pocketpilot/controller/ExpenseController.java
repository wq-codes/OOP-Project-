package com.pocketpilot.controller;

import com.pocketpilot.manager.BudgetManager;
import com.pocketpilot.manager.ExpenseManager;
import com.pocketpilot.model.Expense;
import com.pocketpilot.util.Session;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class ExpenseController {

    @FXML
    private TextField amount;

    @FXML
    private TextField category;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TableView<Expense> expensetable;

    @FXML
    private Label labelexpense;

    @FXML
    private TableColumn<Expense, String> categorycolumn;

    @FXML
    private TableColumn<Expense, Double> amountcolumn;

    @FXML
    private TableColumn<Expense, LocalDate> datecolumn;

    @FXML
    private TableColumn<Expense, String> notecolumn;


    ExpenseManager expenseManager=new ExpenseManager();


    @FXML
    private void initialize() {

        amount.setOnAction(e -> category.requestFocus());

        category.setOnAction(e -> note.requestFocus());


        categorycolumn.setCellValueFactory(
                new PropertyValueFactory<>("category")
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


        expensetable.setItems(
                FXCollections.observableArrayList(
                        expenseManager.getExpensesForUser(
                                Session.currentUser.getUserId()
                        )
                )
        );

                labelexpense.setText("Total Expense: "+
                       expenseManager.gettotalexpense(
                Session.currentUser.getUserId() ) );
    }

@FXML
   private void handleaddexpense (ActionEvent e) throws IOException {

       Alert alert = new Alert(Alert.AlertType.ERROR);

       if (amount.getText().trim().isEmpty() || category.getText().trim().isEmpty()
               || date.getValue() == null || note.getText().trim().isEmpty()) {
           alert.setTitle("Error");
           alert.setHeaderText(null);
           alert.setContentText("All fields are required");
           alert.showAndWait();

       } else {
           try {
               System.out.println("Adding expense for userId: " + Session.currentUser.getUserId());
               expenseManager.addexpense(Double.parseDouble(amount.getText()), date.getValue(), note.getText()
                       , category.getText(), Session.currentUser.getUserId());

          //updating budget after adding expense
               LocalDate now = LocalDate.now();
               String monthName = now.getMonth().toString();
               monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
               String currentMonth = monthName + " " + now.getYear();

               BudgetManager budgetManager = new BudgetManager();
               budgetManager.addspending(Session.currentUser.getUserId(), currentMonth, Double.parseDouble(amount.getText()));



               expensetable.setItems(
                       FXCollections.observableArrayList(
                               expenseManager.getExpensesForUser(
                                       Session.currentUser.getUserId()











                               )
                       )
               );
               System.out.println("Refreshed table with: " + expenseManager.getExpensesForUser(Session.currentUser.getUserId()).size() + " expenses");

               labelexpense.setText("Total Expense: " + expenseManager.gettotalexpense(
                       Session.currentUser.getUserId()
               ));

               amount.clear();
               category.clear();
               date.setValue(null);
               note.clear();

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