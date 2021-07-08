package com.chocolatedevelopers.whistleblower.data.model;


public class Transactions {
    private int transactionId;
    private int userId;
    private double amount;
    private String itemBought;
    private String date;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;

    public Transactions() {
    }

    public Transactions(int userId, double amount, String itemBought, String date,
                        TransactionStatus transactionStatus, TransactionType transactionType) {
        this.userId = userId;
        this.amount = amount;
        this.itemBought = itemBought;
        this.date = date;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItemBought() {
        return itemBought;
    }

    public void setItemBought(String itemBought) {
        this.itemBought = itemBought;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}


