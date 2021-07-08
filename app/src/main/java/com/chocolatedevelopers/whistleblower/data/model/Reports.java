package com.chocolatedevelopers.whistleblower.data.model;


public class Reports {
    private int reportsId;
    private String month;
    private double startBalance;
    private double endBalance;
    private double profit;
    private int numberOfFlaggedTransactions;

    public Reports() {
    }

    public Reports(int reportsId, String month, double startBalance, double endBalance,
                   double profit, int numberOfFlaggedTransactions) {
        this.reportsId = reportsId;
        this.month = month;
        this.startBalance = startBalance;
        this.endBalance = endBalance;
        this.profit = profit;
        this.numberOfFlaggedTransactions = numberOfFlaggedTransactions;
    }

    public int getReportsId() {
        return reportsId;
    }

    public void setReportsId(int reportsId) {
        this.reportsId = reportsId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }

    public double getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(double endBalance) {
        this.endBalance = endBalance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getNumberOfFlaggedTransactions() {
        return numberOfFlaggedTransactions;
    }

    public void setNumberOfFlaggedTransactions(int numberOfFlaggedTransactions) {
        this.numberOfFlaggedTransactions = numberOfFlaggedTransactions;
    }
}
