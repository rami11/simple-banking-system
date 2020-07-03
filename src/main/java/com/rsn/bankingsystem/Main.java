package com.rsn.bankingsystem;

import java.util.*;

public class Main {

    private static final Map<String, String> cardNumberToPinMap = new HashMap<>();
    private static boolean loggedIn = false;

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (loggedIn) {
                printLogInMenu();
                try {
                    int choice = scanner.nextInt();
                    if (choice < 0 || choice > 2) {
                        throw new InputMismatchException();
                    }
                    switch (choice) {

                        case 0:
                            System.out.printf("%nBye!%n");
                            System.exit(0);
                            break;

                        case 2:
                            loggedIn = false;
                            System.out.printf("%nYou have successfully logged out!%n%n");
                            break;
                    }

                } catch (InputMismatchException ex) {
                    System.err.printf("Please, choose an option in range 0-2%n%n");
                }
            } else {
                printMainMenu();
                try {
                    int choice = scanner.nextInt();
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
                            logIntoAccount(scanner);
                            break;
                    }

                } catch (InputMismatchException ex) {
                    System.err.printf("Please, choose an option in range 0-2%n%n");
                }
            }
        }
    }

    static void printMainMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    static void printLogInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    static String generatePIN() {
        Random random = new Random();
        StringBuilder pinBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            pinBuilder.append(random.nextInt(10));
        }
        return pinBuilder.toString();
    }

    private static String generateCardNumber() {

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

    private static void createAccount() {
        System.out.println();

        String cardNumber = generateCardNumber();
        System.out.println("Your card number:");
        System.out.println(cardNumber);

        String pin = generatePIN();
        System.out.println("Your card PIN:");
        System.out.printf("%s%n%n", pin);

        cardNumberToPinMap.put(cardNumber, pin);
    }

    private static void logIntoAccount(Scanner scanner) {
        System.out.println();

        System.out.println("Enter your card number:");
        String cardNumber = scanner.next();

        System.out.println("Enter your PIN:");
        String pin = scanner.next();

        System.out.println();
        if (checkLogin(cardNumber, pin)) {
            loggedIn = true;
            System.out.println("You have successfully logged in!");
        } else {
            System.out.println("Wrong card number or PIN!");
        }

        System.out.println();
    }

    private static boolean checkLogin(String cardNumber, String pin) {
        return pin.equals(cardNumberToPinMap.get(cardNumber));
    }
}
