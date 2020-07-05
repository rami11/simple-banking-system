package com.rsn.bankingsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppState {
    private final Map<String, String> cardNumberToPinMap;
    private final Scanner scanner;
    private boolean loggedIn;

    public AppState() {
        this.scanner = new Scanner(System.in);
        this.cardNumberToPinMap = new HashMap<>();
        this.loggedIn = false;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Map<String, String> getCardNumberToPinMap() {
        return cardNumberToPinMap;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
