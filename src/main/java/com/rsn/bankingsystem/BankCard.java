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
        for (int i = 0; i < 9; i++) {
            int next = random.nextInt(10);
            cardNumberBuilder.append(next);
        }
        int checksum = findChecksum(cardNumberBuilder.toString());
        cardNumberBuilder.append(checksum);

        return cardNumberBuilder.toString();
    }

    private int findChecksum(String cardNumber) {
        char[] chars = cardNumber.toCharArray();

        int sum = 0;
        for (int i = 1; i <= chars.length; i++) {
            int num = Character.getNumericValue(chars[i - 1]);
            if (i % 2 == 1) {
                num *= 2;
            }
            if (num > 9) {
                num -= 9;
            }
            sum += num;
        }
        return sum % 10 == 0 ? 0 : 10 - sum % 10;
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
