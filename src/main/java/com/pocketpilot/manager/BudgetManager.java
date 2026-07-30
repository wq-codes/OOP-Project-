package com.pocketpilot.manager;

import com.pocketpilot.model.Budget;
import com.pocketpilot.model.Income;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetManager {

    ArrayList<Budget> budgetlist;
    int counter;


    public BudgetManager() {

        budgetlist = new ArrayList<>();
        try {
            loadbudget();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        counter = gethighestID() + 1;
    }



    public void loadbudget() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("budget.txt"));

        String line;
        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            Budget budget = new Budget(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
            budgetlist.add(budget);
        }
        reader.close();

    }

    public void savebudget() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("budget.txt"));

        for (Budget b : budgetlist) {
            String line = (b.getBudgetId() + "," + b.getUserId() + "," + b.getMonth() + "," + b.getLimitAmount());
            bw.write(line);
            bw.newLine();
        }
        bw.close();

    }

    public String gen_budgetID() {

        String id = "BG00" + counter;
        counter++;
        return id;
    }

    public int gethighestID() {

        int highest = 0;

        for (Budget b : budgetlist) {


            String outnum = b.getBudgetId().substring(2);
            int result = Integer.parseInt(outnum);

            if (result > highest) {
                highest = result;
            }
        }
        return highest;
    }

    public Budget getbudget(String month) {
        for (Budget b : budgetlist) {

            if (b.getMonth().equals(month)) {
                return b;
            }

        }
        return null;
    }

    public void addspending(String month, double amount) throws IOException {
        for (Budget b : budgetlist) {

            if (b.getMonth().equals(month)) {
                b.addSpending(amount);
                savebudget();
                return;
            }
        }
    }

    public boolean isbudgetexceeded(String month) {
        boolean result = false;
        for (Budget b : budgetlist) {

            if (b.getMonth().equals(month)) {
                result = b.isBudgetExceeded();
              return result;
            }
        }

  return false;
    }

    public double  getRemainingamount(String month){
        double remaining=0;
        for (Budget b : budgetlist) {

            if (b.getMonth().equals(month)) {
              remaining= b.getRemainingAmount();
            return remaining;
    }

}
       return 0;
    }


    public void setbuget(String userId,String month,double limitAmount) throws IOException {

        String generatedID = gen_budgetID();
         Budget budget= new Budget(generatedID,userId,month,limitAmount);
        budgetlist.add(budget);
        savebudget();

    }
}