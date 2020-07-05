package com.rsn.bankingsystem;

import java.util.Random;

public class BankCard {
    private final String cardNumber;
    private final String pin;

    public BankCard() {
        this.cardNumber = generateCardNumber();
        this.pin = generatePIN();
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumberBuilder = new StringBuilder();
        String iin = "400000";
        cardNumberBuilder.append(iin);
        for (int i = 0; i < 10; i++) {
            int next = random.nextInt(10);
            cardNumberBuilder.append(next);
        }
        return cardNumberBuilder.toString();
    }

    private String generatePIN() {
        Random random = new Random();
        StringBuilder pinBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            pinBuilder.append(random.nextInt(10));
        }
        return pinBuilder.toString();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }
}
