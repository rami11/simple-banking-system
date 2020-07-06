package com.rsn.bankingsystem;

public class LoginMenuHandler {
    private final AppState state;

    public LoginMenuHandler(AppState state) {
        this.state = state;
    }

    public void start() {
        printLogInMenu();
        int option = state.readOption();
        switch (option) {
            case 0:
                System.out.printf("%nBye!%n%n");
                System.exit(0);
                break;

            case 1:
                System.out.printf("%nBalance: 0%n%n");
                break;

            case 2:
                state.logout();
                System.out.printf("%nYou have successfully logged out!%n%n");
                break;
            default:
                System.err.printf("%nPlease, choose an option in range 0-2%n%n");
                break;
        }
    }

    private void printLogInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }
}
