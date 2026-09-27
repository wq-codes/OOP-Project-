package com.pocketpilot.model;

public class Budget {

    private String budgetId;
    private String userId;
    private String month;
    private double limitAmount;
    private double spentAmount;

    public Budget(String budgetId, String userId, String month, double limitAmount) {

        this.budgetId = budgetId;
        this.userId = userId;

        if (month == null || month.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid month");
        }
        this.month = month;

        if (limitAmount < 0) {
            throw new IllegalArgumentException("Limit amount cannot be negative");
        }
        this.limitAmount = limitAmount;

        this.spentAmount = 0;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMonth() {
        return month;
    }

    public double getLimitAmount() {
        return limitAmount;
    }

    public double getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(double spentAmount) {
        this.spentAmount = spentAmount;
    }

    public double getRemainingAmount() {
        return limitAmount - spentAmount;
    }

    public void addSpending(double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        spentAmount += amount;
    }

    public boolean isBudgetExceeded() {
        return spentAmount > limitAmount;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId='" + budgetId + '\'' +
                ", userId='" + userId + '\'' +
                ", month='" + month + '\'' +
                ", limitAmount=" + limitAmount +
                ", spentAmount=" + spentAmount +
                '}';
    }

    //setting budgetlimit
    public void setLimitAmount(double limitAmount) {
        if (limitAmount < 0) {
            throw new IllegalArgumentException("Limit amount cannot be negative");
        }
        this.limitAmount = limitAmount;
    }
}