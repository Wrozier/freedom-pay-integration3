package com.princesscurise.com.freedompayintegration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreedomPayIntegrationApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void testPayment() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String url = "http://localhost:" + port + "/payment?amount=9.99&currency=USD&token=12345";
        String response = restTemplate.getForObject(url, String.class);
        assertThat(response).contains("Payment successful");
    }

}