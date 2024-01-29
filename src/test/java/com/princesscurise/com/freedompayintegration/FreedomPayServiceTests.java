package com.princesscurise.com.freedompayintegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FreedomPayServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private FreedomPayService freedomPayService;

    @Test
    public void makePayment_Successful() {
        // Mocking the behavior of restTemplate and purchaseRepository
        when(restTemplate.postForObject(any(String.class), any(PurchaseDetails.class), any(Class.class)))
                .thenReturn("{ \"status\": \"success\", \"message\": \"Payment processed successfully\" }");

        // Mocking the behavior of save method in PurchaseRepository
        when(purchaseRepository.save(any(Purchase.class))).thenAnswer(invocation -> {
            Purchase purchase = invocation.getArgument(0);
            purchase.setId(1L); // Set an example ID for the saved Purchase
            return purchase;
        });

        // Creating a sample PurchaseDetails object
        PurchaseDetails purchaseDetails = new PurchaseDetails(123L, 50.0);

        // Calling the method to be tested
        String result = freedomPayService.makePayment(purchaseDetails);

        // Verifying the result
        assertEquals("Payment successful", result);
    }

    @Test
    public void makePayment_Failure() {
        // Mocking the behavior of restTemplate
        when(restTemplate.postForObject(any(String.class), any(PurchaseDetails.class), any(Class.class)))
                .thenReturn("{ \"status\": \"failure\", \"message\": \"Payment failed\" }");

        // Creating a sample PurchaseDetails object
        PurchaseDetails purchaseDetails = new PurchaseDetails(456L, 30.0);

        // Calling the method to be tested
        String result = freedomPayService.makePayment(purchaseDetails);

        // Verifying the result
        assertEquals("Payment failed. Check API response for details.", result);
    }
}
