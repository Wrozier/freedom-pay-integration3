package com.princesscurise.com.freedompayintegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;

@Service
public class FreedomPayService {

    private final RestTemplate restTemplate;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public FreedomPayService(RestTemplate restTemplate, PurchaseRepository purchaseRepository) {
        this.restTemplate = restTemplate;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public String makePayment(PurchaseDetails purchaseDetails) {
        try {
            validatePurchaseDetails(purchaseDetails);

            // Save purchase details to the database
            Purchase purchase = new Purchase("", 0); // Provide an empty string or a default value for productId
            purchase.setProductId(purchaseDetails.getProductId().toString()); // Convert Long to String
            purchase.setAmount(purchaseDetails.getAmount());
            // Set other details

            purchaseRepository.save(purchase);

            // Make a payment using FreedomPay API
            String apiResponse = makeFreedomPayApiCall(purchaseDetails);

            // Handle API response
            handleApiResponse(apiResponse);

            return "Payment successful";
        } catch (PurchaseNotFoundException e) {
            // Log the exception or perform additional error handling
            return "Payment failed. Purchase not found.";
        } catch (Exception e) {
            // Log the exception or perform additional error handling
            return "Payment failed. Error: " + e.getMessage();
        }
    }

    private void validatePurchaseDetails(PurchaseDetails purchaseDetails) {
        if (purchaseDetails == null || purchaseDetails.getProductId() == null || purchaseDetails.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid purchase details");
        }
    }

    private String makeFreedomPayApiCall(PurchaseDetails purchaseDetails) {
        // Implement the logic to make a payment using FreedomPay API
        // Use apiKey and apiSecret to authenticate with FreedomPay
        // Make API calls and return the response
        // For example:
         //String apiUrl = "https://api.freedompay.com/payments";
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", "Bearer yourAccessToken");
        // HttpEntity<PurchaseDetails> requestEntity = new HttpEntity<>(purchaseDetails, headers);
        // ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
        // return responseEntity.getBody();

        // For simplicity, return a mock response
        return "{ \"status\": \"success\", \"message\": \"Payment processed successfully\" }";
    }

    private void handleApiResponse(String apiResponse) {
        // Implement your logic to handle the API response
        // This can include parsing the response, checking for success, logging, etc.

        // For example, a simple print statement
        System.out.println("API Response: " + apiResponse);

        // You may want to check the response content and take appropriate actions
        if (apiResponse.contains("\"status\": \"success\"")) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed. Check API response for details.");
            throw new PurchaseNotFoundException("Purchase not found in the API response");
        }
    }
}
