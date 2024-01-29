package com.princesscurise.com.freedompayintegration;

public class PurchaseDetails {

    private Long productId;
    private double amount;

    // Add other fields as needed

    // Constructors, getters, and setters

    public PurchaseDetails() {
    }

    public PurchaseDetails(Long productId, double amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Add other getters and setters as needed
}
