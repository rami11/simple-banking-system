package com.rsn.bankingsystem;

import java.util.InputMismatchException;

public class MainMenuHandler {
    private final AppState state;

    public MainMenuHandler(AppState state) {
        this.state = state;
    }

    private void createAccount() {
        System.out.println();

        BankCard bankCard = new BankCard();
        String cardNumber = bankCard.getCardNumber();
        String pin = bankCard.getPin();

        System.out.println("Your card number:");
        System.out.println(cardNumber);

        System.out.println("Your card PIN:");
        System.out.printf("%s%n%n", pin);

        state.getCardNumberToPinMap().put(cardNumber, pin);
    }

    private void logIntoAccount() {
        System.out.println();

        System.out.println("Enter your card number:");
        String cardNumber = state.getScanner().next();

        System.out.println("Enter your PIN:");
        String pin = state.getScanner().next();

        if (checkLogin(cardNumber, pin)) {
            state.setLoggedIn(true);
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nWrong card number or PIN!");
        }

        System.out.println();
    }

    public void start() {
        printMainMenu();
        try {
            int choice = state.getScanner().nextInt();
            if (choice < 0 || choice > 2) {
                throw new InputMismatchException();
            }

            switch (choice) {
                case 0:
                    System.out.printf("%nBye!%n");
                    System.exit(0);
                    break;
                case 1:
                    createAccount();
                    break;
                case 2:
                    logIntoAccount();
                    break;
            }

        } catch (InputMismatchException ex) {
            System.err.printf("Please, choose an option in range 0-2%n%n");
        }
    }

    private void printMainMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    private boolean checkLogin(String cardNumber, String pin) {
        return pin.equals(state.getCardNumberToPinMap().get(cardNumber));
    }
}
