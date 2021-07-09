package com.chocolatedevelopers.whistleblower.data.model;

public class Levels {
    private int levelId;
    private int cardId;

    //I changed these to String from double for ease
    //Because the database is saving 'CHAR'.
    private String budgetAmount;
    private String balance;
    private String profitExpected;
    private String levelSalary;
    private String transactionLimit;

    public Levels() {
    }

    public Levels(int levelId, int cardId, String budgetAmount, String balance, String profitExpected, String levelSalary, String transactionLimit) {
        this.levelId = levelId;
        this.cardId = cardId;
        this.budgetAmount = budgetAmount;
        this.balance = balance;
        this.profitExpected = profitExpected;
        this.levelSalary = levelSalary;
        this.transactionLimit = transactionLimit;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(String budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getProfitExpected() {
        return profitExpected;
    }

    public void setProfitExpected(String profitExpected) {
        this.profitExpected = profitExpected;
    }

    public String getLevelSalary() {
        return levelSalary;
    }

    public void setLevelSalary(String levelSalary) {
        this.levelSalary = levelSalary;
    }

    public String getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(String transactionLimit) {
        this.transactionLimit = transactionLimit;
    }
}