package com.pocketpilot.model;

import java.time.LocalDate;
import java.util.Date;

public class Expense extends Transaction {

        private String category;

        public Expense(String transactionId, double amount, LocalDate date, String description,String userId, String category) {
            super(transactionId, amount, date, description,userId);

            if (category == null || category.trim().isEmpty()) {
                throw new IllegalArgumentException("Category cannot be empty");
            }

            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            if (category == null || category.trim().isEmpty()) {
                throw new IllegalArgumentException("Category cannot be empty");
            }

            this.category = category;
        }

        @Override
        public String toString() {
            return super.toString() + ", Category: " + category;
        }
    }



