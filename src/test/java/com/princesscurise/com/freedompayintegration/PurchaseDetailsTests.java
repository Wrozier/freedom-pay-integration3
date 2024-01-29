package com.princesscurise.com.freedompayintegration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseDetailsTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPurchaseDetailsCreation() {
        // Arrange
        Long productId = 123L;
        double amount = 50.0;

        // Act
        PurchaseDetails purchaseDetails = new PurchaseDetails(productId, amount);

        // Assert
        assertNotNull(purchaseDetails);
        assertEquals(productId, purchaseDetails.getProductId());
        assertEquals(amount, purchaseDetails.getAmount(), 0.001); // Add a delta for double comparison
    }

    @Test
    public void testPurchaseDetailsInvalidCreation() {
        // Arrange
        expectedException.expect(IllegalArgumentException.class);

        // Act
        new PurchaseDetails(null, 50.0);
    }

    @Test
    public void testPurchaseDetailsNegativeAmountCreation() {
        // Arrange
        expectedException.expect(IllegalArgumentException.class);

        // Act
        new PurchaseDetails(123L, -50.0);
    }

    // Add more tests based on your actual use cases and behavior associated with PurchaseDetails
}
