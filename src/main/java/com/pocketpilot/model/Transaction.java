package com.pocketpilot.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    protected String  transactionId;
   protected  double amount;
     protected LocalDate date;
   protected  String description;
protected String userId;

    public Transaction(String transactionId, double amount, LocalDate date, String description,String userId) {
        this.transactionId = transactionId;

      if (amount<=0){
          throw new IllegalArgumentException("Amount can't be negative or  zero");
      }
        this.amount = amount;

         this.date = date;
          this.description = description;

            this.userId=userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String  getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
          if (!(obj instanceof Transaction))
              return false;

          Transaction t=(Transaction) obj;
          return  this.transactionId.equals(t.transactionId);
    }
}
