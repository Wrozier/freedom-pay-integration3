package com.princesscurise.com.freedompayintegration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreedomPayIntegrationApplication.class)
public class PurchaseTests {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void savePurchaseTest() {
        // Given
        Purchase purchase = new Purchase("ABC123", 50.0);

        // When
        purchaseRepository.save(purchase);

        // Then
        assertNotNull(purchase.getId()); // Ensure ID is generated after save
    }

    @Test
    public void findPurchaseByIdTest() {
        // Given
        Purchase purchase = new Purchase("DEF456", 75.0);
        purchaseRepository.save(purchase);

        // When
        Purchase foundPurchase = purchaseRepository.findById(purchase.getId()).orElse(null);

        // Then
        assertNotNull(foundPurchase);
        assertEquals("DEF456", foundPurchase.getProductId());
        assertEquals(75.0, foundPurchase.getAmount(), 0.001);
    }

    @Test
    public void findAllPurchasesTest() {
        // Given
        purchaseRepository.save(new Purchase("GHI789", 100.0));
        purchaseRepository.save(new Purchase("JKL012", 25.0));

        // When
        List<Purchase> purchases = purchaseRepository.findAll();

        // Then
        assertNotNull(purchases);
        assertEquals(2, purchases.size());
    }

    // Add more tests as needed for your project
}
