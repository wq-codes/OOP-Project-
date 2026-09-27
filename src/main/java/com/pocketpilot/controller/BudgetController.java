package com.pocketpilot.controller;

import com.pocketpilot.manager.BudgetManager;
import com.pocketpilot.model.Budget;
import com.pocketpilot.util.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalDate;

public class BudgetController {

    @FXML
    private HBox statusBox;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private Label limitLabel;

    @FXML
    private Label spentLabel;

    @FXML
    private Label remainingLabel;

    @FXML
    private Label percentLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private ProgressIndicator budgetProgressIndicator;

    @FXML
    private ProgressBar budgetProgressBar;

    @FXML
    private Label statusMessageLabel;

    @FXML
    private Label statusLimitLabel;

    @FXML
    private Label statusSpentLabel;

    @FXML
    private Label statusRemainingLabel;

    @FXML
    private Button setBudgetButton;

    BudgetManager budgetManager = new BudgetManager();


    @FXML
    private void initialize() {

        // populate month dropdown with a few months
        monthComboBox.setItems(FXCollections.observableArrayList(
                "January 2026", "February 2026", "March 2026", "April 2026",
                "May 2026", "June 2026", "July 2026", "August 2026",
                "September 2026", "October 2026", "November 2026", "December 2026"
        ));

        // default selection = the actual current month, not a hardcoded one
        LocalDate now = LocalDate.now();
        String monthName = now.getMonth().toString();
        monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
        String currentMonth = monthName + " " + now.getYear();
        monthComboBox.setValue(currentMonth);

        monthComboBox.setOnAction(e -> refreshBudgetView());

        refreshBudgetView();
    }


    // pulls the right Budget for the selected month + logged-in user, updates every label/progress element
    private void refreshBudgetView() {

        String userId = Session.currentUser.getUserId();
        String month = monthComboBox.getValue();

        Budget budget = budgetManager.getbudget(userId, month);

        if (budget == null) {
            // no budget set yet for this month
            limitLabel.setText("PKR 0");
            spentLabel.setText("PKR 0");
            remainingLabel.setText("PKR 0");
            percentLabel.setText("0%");
            budgetProgressIndicator.setProgress(0);
            budgetProgressBar.setProgress(0);
            statusLabel.setText("No budget set for this month.");
            statusMessageLabel.setText("No budget set for this month.");
            statusLimitLabel.setText("PKR 0");
            statusSpentLabel.setText("PKR 0");
            statusRemainingLabel.setText("PKR 0");
            return;
        }

        double limit = budget.getLimitAmount();
        double spent = budget.getSpentAmount();
        double remaining = budgetManager.getRemainingamount(userId, month);

        double ratio = (limit == 0) ? 0 : (spent / limit);
        int percent = (int) (ratio * 100);

        limitLabel.setText("PKR " + limit);
        spentLabel.setText("PKR " + spent);
        remainingLabel.setText("PKR " + remaining);
        percentLabel.setText(percent + "%");

        budgetProgressIndicator.setProgress(ratio);
        budgetProgressBar.setProgress(ratio);

        statusLimitLabel.setText("PKR " + limit);
        statusSpentLabel.setText("PKR " + spent);
        statusRemainingLabel.setText("PKR " + remaining);

        boolean exceeded = budgetManager.isbudgetexceeded(userId, month);

        if (exceeded) {
            String msg = "You have exceeded your monthly budget!";
            statusLabel.setText(msg);
            statusMessageLabel.setText(msg);
            statusLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12px;");
            statusMessageLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12px;");
            statusBox.setStyle("-fx-background-color: #311414; -fx-background-radius: 8; -fx-border-color: #ef4444; -fx-border-radius: 8;");
        } else {
            String msg = "You are within your monthly budget.";
            statusLabel.setText(msg);
            statusMessageLabel.setText(msg);
            statusLabel.setStyle("-fx-text-fill: #4ade80; -fx-font-size: 12px;");
            statusMessageLabel.setStyle("-fx-text-fill: #4ade80; -fx-font-size: 12px;");
            statusBox.setStyle("-fx-background-color: #14311f; -fx-background-radius: 8; -fx-border-color: #22c55e; -fx-border-radius: 8;");
        }
    }
    @FXML
    private void handleSetBudget(ActionEvent event) throws IOException {

        String userId = Session.currentUser.getUserId();
        String month = monthComboBox.getValue();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set / Update Budget");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter budget limit for " + month + ":");

        dialog.showAndWait().ifPresent(input -> {

            try {
                double limitAmount = Double.parseDouble(input.trim());

                Budget existing = budgetManager.getbudget(userId, month);

                try {
                    if (existing == null) {
                        budgetManager.setbuget(userId, month, limitAmount);
                    } else {
                        budgetManager.updateBudget(userId, month, limitAmount);
                    }
                } catch (IOException ex) {
                    showError("Could not save budget.");
                }

                refreshBudgetView();

            } catch (NumberFormatException ex) {
                showError("Please enter a valid number.");
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}