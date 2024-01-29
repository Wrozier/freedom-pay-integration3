package com.princesscurise.com.freedompayintegration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private double amount;

    // Other necessary details (add more fields as needed)

    // Constructors (you can generate these based on your needs)

   // public Purchase() {
   // }

    public Purchase(String productId, double amount) {
        this.productId = productId;
        this.amount = amount;
        // Set other fields as needed
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Add getters and setters for other fields as needed

    // toString() method (for easy debugging)

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                // Include other fields in the toString() representation
                '}';
    }
}
