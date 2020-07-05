package com.rsn.bankingsystem;

import java.util.InputMismatchException;

public class LoginMenuHandler {
    private final AppState state;

    public LoginMenuHandler(AppState state) {
        this.state = state;
    }

    public void start() {
        printLogInMenu();
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

                case 2:
                    state.setLoggedIn(false);
                    System.out.printf("%nYou have successfully logged out!%n%n");
                    break;
            }

        } catch (InputMismatchException ex) {
            System.err.printf("Please, choose an option in range 0-2%n%n");
        }
    }

    private void printLogInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }
}
