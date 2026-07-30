package com.pocketpilot.manager;

import com.pocketpilot.model.Expense;
import com.pocketpilot.model.Income;
import com.pocketpilot.model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IncomeManager {

       ArrayList<Income>   incomelist;
      int counter ;

    public IncomeManager() {
       incomelist=new ArrayList<>();
        try {
            loadincome();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        counter = gethighestID() + 1;
    }



    public void loadincome() throws IOException{

        BufferedReader reader= new BufferedReader(new FileReader("income.txt"));

        String line;
        while ((line=reader.readLine())!=null){

          String [] parts=  line.split(",");

            Income income=new Income(parts[0],Double.parseDouble(parts[1]), LocalDate.parse(parts[2]),parts[3],parts[4],parts[5]);
            incomelist.add(income);
        }
         reader.close();
    }

    public void saveincome () throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("income.txt"));

        for (Income i : incomelist) {
            String line = (i.getTransactionId() + "," + i.getAmount() + "," + i.getDate() + "," + i.getDescription()+","+i.getSource()+","+i.getUserId());
            bw.write(line);
            bw.newLine();
        }
        bw.close();

    }
    public String gen_incomeID() {

        String id = "IN00" + counter;
        counter++;
        return id;
    }

    public int gethighestID() {
        int highest = 0;
        for (Income income : incomelist) {

            String numPart = income.getTransactionId().substring(2);

            int num = Integer.parseInt(numPart);

            if (num > highest) {
                highest = num;
            }
        }
        return highest;
    }

    public void deleteincome(String transaction) {


        for (Income i:incomelist) {

            if (i.getTransactionId().equals(transaction)) {

                incomelist.remove(i);

            }
        }
    }

    public double gettotalincome(String userId) {
        double totalincome = 0;
        for (Income i : incomelist) {
            if (i.getUserId().equals(userId)) {
                totalincome += i.getAmount();
            }
        }
        return totalincome;
    }

    public void addincome(double amount, LocalDate date, String description, String source,String userId) throws IOException {

        String generatedID = gen_incomeID();
        Income income= new Income(generatedID, amount, date, description, source,userId);
        incomelist.add(income);
        saveincome();

    }
    public Map<String, Double> getincomeBysource(String userId ){
        Map<String, Double> incomes = new HashMap<>();


        for (Income i : incomelist) {


             if (i.getUserId().equals(userId)){
            if (incomes.containsKey(i.getSource())) {

                double currenttotal = incomes.get(i.getSource());
                incomes.put(i.getSource(), currenttotal + i.getAmount());
            } else {
                incomes.put(i.getSource(), i.getAmount());

            }    }
        }
        return incomes;
    }

}
