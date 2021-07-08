package com.chocolatedevelopers.whistleblower.model;

public class TransactionDetails {
    String item, quantity, username, amount, date, time;
    boolean isFlagged;

    public TransactionDetails(String item, String quantity, String username, String amount, String date, String time, boolean isFlagged) {
        this.item = item;
        this.quantity = quantity;
        this.username = username;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.isFlagged = isFlagged;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}