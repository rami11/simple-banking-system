package com.rsn.bankingsystem;

import java.util.Objects;
import java.util.Random;

public class BankCard {
    private final int id;
    private final String cardNumber;
    private final String pin;
    private int balance;

    public BankCard(int id, String cardNumber, String pin, int balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public BankCard() {
        this.id = -1;
        this.cardNumber = generateCardNumber();
        this.pin = generatePIN();
        this.balance = 0;
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
        return (10 - sum % 10) % 10;
    }

    private String generatePIN() {
        Random random = new Random();
        StringBuilder pinBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            pinBuilder.append(random.nextInt(10));
        }
        return pinBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(cardNumber, bankCard.cardNumber) &&
                Objects.equals(pin, bankCard.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, pin);
    }
}
