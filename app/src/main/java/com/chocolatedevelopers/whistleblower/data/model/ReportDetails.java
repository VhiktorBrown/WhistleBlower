package com.chocolatedevelopers.whistleblower.data.model;

public class ReportDetails {
    String reportMonth, startBalance, endBalance, profit, flaggedTransactions;

    public ReportDetails(String reportMonth, String startBalance, String endBalance, String profit, String flaggedTransactions) {
        this.reportMonth = reportMonth;
        this.startBalance = startBalance;
        this.endBalance = endBalance;
        this.profit = profit;
        this.flaggedTransactions = flaggedTransactions;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    public String getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getFlaggedTransactions() {
        return flaggedTransactions;
    }

    public void setFlaggedTransactions(String flaggedTransactions) {
        this.flaggedTransactions = flaggedTransactions;
    }
}
