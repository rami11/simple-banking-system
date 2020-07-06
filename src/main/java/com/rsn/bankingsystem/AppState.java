package com.rsn.bankingsystem;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AppState {
    private final Scanner scanner;
    private final Map<String, BankCard> cardMap;
    private BankCard loggedInCard;

    public AppState() {
        this.cardMap = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public boolean tryLogin(String cardNumber, String pin) {
        BankCard card = cardMap.get(cardNumber + "_" + pin);
        if (card != null) {
            loggedInCard = card;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInCard = null;
    }

    public boolean isLoggedIn() {
        return loggedInCard != null;
    }

    public int readOption() {
        try {
            String str = this.scanner.next();
            return Integer.parseInt(str);
        } catch (InputMismatchException | NumberFormatException ex) {
            return -1;
        }
    }

    public String readNext() {
        return this.scanner.next();
    }

    public void addCard(BankCard card) {
        cardMap.put(card.getCardNumber() + "_" + card.getPin(), card);
    }
}
