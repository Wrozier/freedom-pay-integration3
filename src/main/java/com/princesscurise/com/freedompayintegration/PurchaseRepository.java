package com.princesscurise.com.freedompayintegration;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // You can add custom query methods if needed
    // For example, find purchases by productId, amount, etc.
	
	  // Example: Find purchases by productId
    List<Purchase> findByProductId(String productId);

    // Example: Find purchases by amount greater than a certain value
    List<Purchase> findByAmountGreaterThan(double amount);

}
