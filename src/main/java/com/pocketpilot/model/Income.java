package com.pocketpilot.model;

import java.util.Date;

public class Income extends Transaction {

    private String source;

    public Income(String transactionId, double amount, Date date, String description, String source) {
        super(transactionId, amount, date, description);

        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("Source cannot be empty");
        }

        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (source == null || source.trim().isEmpty()) {
            throw new IllegalArgumentException("Source cannot be empty");
        }

        this.source = source;
    }

    @Override
    public String toString() {
        return super.toString() + ", Source: " + source;
    }
}