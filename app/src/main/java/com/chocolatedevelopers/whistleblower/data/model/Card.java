package com.chocolatedevelopers.whistleblower.data.model;


public class Card {
    private int cardId;
    private String cardNumber;
    private String cardCVV;
    private String cardExpiryDate;

    public Card() {
    }

    public Card(String cardNumber, String cardCVV, String cardExpiryDate) {
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
        this.cardExpiryDate = cardExpiryDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }
}
