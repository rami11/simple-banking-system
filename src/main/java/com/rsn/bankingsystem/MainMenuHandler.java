package com.rsn.bankingsystem;

import java.sql.SQLException;

public class MainMenuHandler {
    private final AppState state;

    public MainMenuHandler(AppState state) {
        this.state = state;
    }

    private void createAccount() throws SQLException {
        BankCard bankCard = new BankCard();
        state.addCard(bankCard);

        String cardNumber = bankCard.getCardNumber();
        String pin = bankCard.getPin();
        System.out.println("\nYour card number:");
        System.out.println(cardNumber);

        System.out.println("Your card PIN:");
        System.out.printf("%s%n%n", pin);
    }

    private void logIntoAccount() throws SQLException {
        System.out.println("\nEnter your card number:");
        String cardNumber = state.readNext();

        System.out.println("Enter your PIN:");
        String pin = state.readNext();

        if (state.tryLogin(cardNumber, pin)) {
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nWrong card number or PIN!");
        }
        System.out.println();
    }

    public void start() {
        printMainMenu();
        int option = state.readOption();
        switch (option) {
            case 0:
                System.out.printf("%nBye!%n");
                System.exit(0);
                break;
            case 1:
                try {
                    createAccount();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage() + '\n');
                }
                break;
            case 2:
                try {
                    logIntoAccount();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage() + '\n');
                }
                break;
            default:
                System.out.printf("%nPlease, choose an option in range 0-2%n%n");
                break;
        }
    }

    private void printMainMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }
}
