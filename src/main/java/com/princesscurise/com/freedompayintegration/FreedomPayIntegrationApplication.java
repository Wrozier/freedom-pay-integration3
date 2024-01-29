package com.princesscurise.com.freedompayintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = "com.princesscurise.freedompayintegration")

public class FreedomPayIntegrationApplication {

    // Singleton RestTemplate for making API calls
    private static final RestTemplate restTemplate = new RestTemplate();
    
    

    public static void main(String[] args) {
        SpringApplication.run(FreedomPayIntegrationApplication.class, args);

        // Make payment using FreedomPayment API
        makePayment();
    }

    @Configuration
    public class AppConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
    private static void makePayment() {

        // Payment details
        String amount = "99.95";
        String currency = "USD";
        String token = "12345"; // fetch from front-end

        // Build request URL with parameters
        String url = "https://api.freedompay.com/payments?amount={amount}&currency={currency}" + "&token={token}";

        // Make API call
        String response = restTemplate.getForObject(url, String.class, amount, currency, token);

        // Handle response
        handleApiResponse(response);
    }

    private static void handleApiResponse(String response) {
        // Implement your logic to handle the API response
        // This can include parsing the response, checking for success, logging, etc.

        // For example, a simple print statement
        System.out.println("API Response: " + response);

        // You may want to check the response content and take appropriate actions
        if (response.contains("success")) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed. Check API response for details.");
        }
    }
}
