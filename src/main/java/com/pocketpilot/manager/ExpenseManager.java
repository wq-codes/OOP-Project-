package com.pocketpilot.manager;

import com.pocketpilot.model.Expense;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

            Expense expense = new Expense(parts[0], Double.parseDouble(parts[1]), LocalDate.parse(parts[2]), parts[3], parts[4]);
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
            String line = (e.getTransactionId() + "," + e.getAmount() + "," + e.getDate() + "," + e.getDescription() + "," + e.getCategory());
            bw.write(line);
            bw.newLine();
        }
        bw.close();

    }

    public void addexpense(double amount , LocalDate date , String description, String category) throws IOException {

          String generatedID = gen_expenseID();
           Expense expense = new Expense (generatedID, amount,  date, description, category);
              expenselist.add(expense);
           saveexpense();

        }
    }











