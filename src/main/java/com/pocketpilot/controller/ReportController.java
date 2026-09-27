package com.pocketpilot.controller;

import com.pocketpilot.manager.BudgetManager;
import com.pocketpilot.manager.ExpenseManager;
import com.pocketpilot.manager.IncomeManager;
import com.pocketpilot.util.Session;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Map;

import java.time.LocalDate;

public class ReportController {
@FXML
private BarChart<String,Number> incomeVsExpenseChart;


    @FXML
private PieChart expenseByCategoryChart;

@FXML
private PieChart incomeBySourceChart;

@FXML
    private Label totalIncomeLabel;

@FXML
    private Label totalExpenseLabel;

@FXML
    private Label netSavingsLabel;
@FXML
private Label budgetStatusLabel;


ExpenseManager expenseManager = new ExpenseManager();

    IncomeManager incomeManager = new IncomeManager();

    BudgetManager budgetManager = new BudgetManager();

@FXML
private void initialize (){


    //only for checking
    System.out.println("Reports initialize running. UserId: " + Session.currentUser.getUserId());
    System.out.println("Total income found: " + incomeManager.gettotalincome(Session.currentUser.getUserId()));
    System.out.println("Total expense found: " + expenseManager.gettotalexpense(Session.currentUser.getUserId()));





    totalIncomeLabel.setText("PKR " + incomeManager.gettotalincome(Session.currentUser.getUserId()));
    totalExpenseLabel.setText("PKR " + expenseManager.gettotalexpense(Session.currentUser.getUserId()));

    netSavingsLabel.setText("PKR "+String.valueOf(incomeManager.gettotalincome(Session.currentUser.getUserId())-
            expenseManager.gettotalexpense(Session.currentUser.getUserId())));

    LocalDate now = LocalDate.now();
    String monthName = now.getMonth().toString();
    monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
    String currentMonth = monthName + " " + now.getYear();

    boolean exceeded = budgetManager.isbudgetexceeded(Session.currentUser.getUserId(), currentMonth);

    if (exceeded) {
        budgetStatusLabel.setText("Over Budget");
        budgetStatusLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 15px; -fx-font-weight: bold;");
    } else {
        budgetStatusLabel.setText("Within Budget");
        budgetStatusLabel.setStyle("-fx-text-fill: #facc15; -fx-font-size: 15px; -fx-font-weight: bold;");
    }



    Map<String, Double> categoryTotals = expenseManager.getExpensesByCategory(Session.currentUser.getUserId());

    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

    for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
        pieData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
    }

    expenseByCategoryChart.setData(pieData);


    Map<String, Double> categoryTotal = incomeManager.getincomeBysource(Session.currentUser.getUserId());

    ObservableList<PieChart.Data> pieDataincome = FXCollections.observableArrayList();

    for (Map.Entry<String, Double> entry : categoryTotal.entrySet()) {
        pieDataincome.add(new PieChart.Data(entry.getKey(), entry.getValue()));
    }
    incomeBySourceChart.setData(pieDataincome);


    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.getData().add(new XYChart.Data<>("Total Income", incomeManager.gettotalincome(Session.currentUser.getUserId())));
    series.getData().add(new XYChart.Data<>("Total Expenses", expenseManager.gettotalexpense(Session.currentUser.getUserId())));

    incomeVsExpenseChart.getData().add(series);






}







}
