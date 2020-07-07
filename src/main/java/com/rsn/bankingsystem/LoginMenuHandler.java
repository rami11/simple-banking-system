package com.rsn.bankingsystem;

import java.sql.SQLException;

public class LoginMenuHandler {
    private final AppState state;

    public LoginMenuHandler(AppState state) {
        this.state = state;
    }

    public void start() {
        printLogInMenu();
        int option = state.readOption();
        switch (option) {

            case 1:
                try {
                    System.out.printf("%nBalance: %d%n%n", state.getBalance());
                } catch (SQLException ex) {
                    System.out.printf("%n%s%n%n", ex.getMessage());
                }
                break;

            case 2:
                try {
                    handleAddIncome();
                } catch (SQLException ex) {
                    System.out.printf("%n%s%n%n", ex.getMessage());
                }
                break;

            case 5:
                state.logout();
                System.out.printf("%nYou have successfully logged out!%n%n");
                break;

            case 0:
                System.out.printf("%nBye!%n%n");
                System.exit(0);
                break;

            default:
                System.err.printf("%nPlease, choose an option in range 0-2%n%n");
                break;
        }
    }

    private void handleAddIncome() throws SQLException {
        System.out.printf("%nEnter income:%n");
        try {
            int income = Integer.parseInt(state.readNext());
            state.addIncome(income);
            System.out.println("Income was added!\n");
        } catch (NumberFormatException ex) {
            System.err.printf("%nInvalid entry: %s%n%n", ex.getMessage());
        }
    }

    private void printLogInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }
}
