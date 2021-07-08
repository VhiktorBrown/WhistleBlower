package com.chocolatedevelopers.whistleblower.data.model;


public class Levels {
    private int levelId;
    private int cardId;
    private double budgetAmount;
    private double balance;
    private double profitExpected;
    private double levelSalary;
    private double transactionLimit;

    public Levels() {
    }

    public Levels(int cardId, double budgetAmount, double balance, double profitExpected,
                  double levelSalary, double transactionLimit) {
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

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfitExpected() {
        return profitExpected;
    }

    public void setProfitExpected(double profitExpected) {
        this.profitExpected = profitExpected;
    }

    public double getLevelSalary() {
        return levelSalary;
    }

    public void setLevelSalary(double levelSalary) {
        this.levelSalary = levelSalary;
    }

    public double getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(double transactionLimit) {
        this.transactionLimit = transactionLimit;
    }
}
