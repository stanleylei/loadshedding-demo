package com.giganobs.loadsheddingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class LoadSheddingController {

    @Autowired
    private LoadSheddingService loadSheddingService;

    @GetMapping("/data")
    @LogExecutionTime
    public String getData() {

        if (!loadSheddingService.requestStart()) {
            throw new LoadSheddingException("Service is currently too busy. Please try again later.");
        }

        try {
            // Simulate processing
            Thread.sleep(1); // Simulate delay

            Random rand = new Random();

            // Obtain a number between [20 - 40].
            int randomNum = rand.nextInt(20) + 20;

            System.out.println("Random Number: " + randomNum);

            long result = fibonacci(randomNum);

            return "Data processed successfully. Fibonacci of " + randomNum + " is: " + result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error processing request";
        } finally {
            loadSheddingService.requestEnd();
        }
    }

    private long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }


}