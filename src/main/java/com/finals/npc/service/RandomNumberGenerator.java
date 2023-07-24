package com.finals.npc.service;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class RandomNumberGenerator {
     public String generateRandomNumber(int numDigits) {
        if (numDigits < 1 || numDigits > 5) {
            throw new IllegalArgumentException("Number of digits must be between 1 and 5.");
        }

        Random random = new Random();
        int upperBound = (int) Math.pow(10, numDigits);
        int randomNumber = random.nextInt(upperBound);

        return String.format("%0" + numDigits + "d", randomNumber);
    }
}
