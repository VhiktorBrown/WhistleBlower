package com.chocolatedevelopers.whistleblower.data.model;

public class TransactionDetails {
    private int transactionId;
    String item, quantity,username, amount, date, time;
    int isFlagged;

    public TransactionDetails(int transactionId, String item, String quantity, String username, String amount, String date, String time, int isFlagged) {
        this.transactionId = transactionId;
        this.item = item;
        this.quantity = quantity;
        this.username = username;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.isFlagged = isFlagged;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    public int getIsFlagged() {
        return isFlagged;
    }

    public void setIsFlagged(int isFlagged) {
        this.isFlagged = isFlagged;
    }
}