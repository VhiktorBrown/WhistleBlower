package com.chocolatedevelopers.whistleblower.data.model;

public class TransactionDetails {
    String name, amount, transaction_type, date, isVerified;

    public TransactionDetails(String name, String amount, String transaction_type, String date, String isVerified) {
        this.name = name;
        this.amount = amount;
        this.transaction_type = transaction_type;
        this.date = date;
        this.isVerified = isVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }
}
