package com.glassdoor.test.intern.first.model;

public class IncomingRequest {
    private int userId;
    private String userName;
    private String billingAddress;
    private double amount;
    private String cardnumber;

    public IncomingRequest() {
    }

    public IncomingRequest(int userId, String userName, String billingAddress, double amount, String cardnumber) {
        this.userId = userId;
        this.userName = userName;
        this.billingAddress = billingAddress;
        this.amount = amount;
        this.cardnumber = cardnumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    @Override
    public String toString() {
        return "IncomingRequest{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", amount=" + amount +
                ", cardnumber='" + cardnumber + '\'' +
                '}';
    }
}
