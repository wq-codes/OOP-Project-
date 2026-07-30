package com.pocketpilot.manager;

import com.pocketpilot.model.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {


    ArrayList<Expense> expenselist;
    int counter;

    public ExpenseManager() {
        expenselist = new ArrayList<>();
        try {
            loadexpense();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        counter = gethighestID() + 1;

    }

    public void loadexpense() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("expense.txt"));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            Expense expense = new Expense(parts[0], Double.parseDouble(parts[1]), LocalDate.parse(parts[2]), parts[3], parts[4],parts[5]);
            expenselist.add(expense);
        }
        reader.close();
    }

    public String gen_expenseID() {

        String id = "EX00" + counter;
        counter++;
        return id;
    }


    public int gethighestID() {
        int highest = 0;
        for (Expense expense : expenselist) {

            String numPart = expense.getTransactionId().substring(2);

            int num = Integer.parseInt(numPart);

            if (num > highest) {
                highest = num;
            }
        }
        return highest;
    }

    public void deleteexpense(String transaction) {


        for (Expense e : expenselist) {

            if (e.getTransactionId().equals(transaction)) {

                expenselist.remove(e);

            }
        }
    }

    public void saveexpense() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("expense.txt"));

        for (Expense e : expenselist) {
            String line = (e.getTransactionId() + "," + e.getAmount() + "," + e.getDate() + "," + e.getDescription() + "," + e.getCategory()+","+e.getUserId());
            bw.write(line);
            bw.newLine();
        }
        bw.close();

    }

    public void addexpense(double amount, LocalDate date, String description, String category,String userId) throws IOException {

        String generatedID = gen_expenseID();
        Expense expense = new Expense(generatedID, amount, date, description, category,userId);
        expenselist.add(expense);
        saveexpense();

    }

    public double gettotalexpense(String userId) {
        double totalexpense = 0;
        for (Expense e : expenselist) {
               if (e.getUserId().equals(userId))
            totalexpense += e.getAmount();
        }
        return totalexpense;
    }

    public Map<String, Double> getExpensesByCategory(String userId) {
        Map<String, Double> expenses = new HashMap<>();


        for (Expense e : expenselist) {


                 if (e.getUserId().equals(userId)){
            if (expenses.containsKey(e.getCategory())) {

                double currenttotal = expenses.get(e.getCategory());
                expenses.put(e.getCategory(), currenttotal + e.getAmount());
            } else {
                expenses.put(e.getCategory(), e.getAmount());

            }   }
        }
            return expenses;
        }

}










