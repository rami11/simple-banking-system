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

            case 3:
                try {
                    handleTransferProcess();
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
                System.out.printf("%nPlease, choose an option in range 0-2%n%n");
                break;
        }
    }

    private void handleTransferProcess() throws SQLException {
        System.out.printf("%nTransfer%n");
        System.out.println("Enter card number:");
        String cardNumber = state.readNext();

        // handle exceptions
        if (cardNumber.equals(state.getLoggedInCardNumber())) {
            System.out.printf("%nYou can't transfer money to the same account!%n%n");
            return;
        }
        if (!state.isLuhnValid(cardNumber)) {
            System.out.printf("%nProbably you made mistake in the card number. Please try again!%n%n");
            return;
        }
        int cardId = state.getCardId(cardNumber);
        if (cardId == -1) {
            System.out.printf("%nSuch a card does not exist.%n%n");
            return;
        }

        // pass
        transferIncome(cardId);
    }

    private void transferIncome(int toCardId) throws SQLException {
        System.out.printf("%nEnter how much money you want to transfer:%n");
        try {
            int moneyToTransfer = Integer.parseInt(state.readNext());
            int balance = state.getBalance();
            if (balance < moneyToTransfer) {
                System.out.printf("%nNot enough money!%n%n");
                return;
            }
            state.transferIncome(toCardId, moneyToTransfer);
            System.out.println("Success!\n");

        } catch (NumberFormatException ex) {
            System.out.printf("%nInvalid entry: %s%n%n", ex.getMessage());
        }
    }

    private void handleAddIncome() throws SQLException {
        System.out.printf("%nEnter income:%n");
        try {
            int income = Integer.parseInt(state.readNext());
            state.addIncome(income);
            System.out.println("Income was added!\n");

        } catch (NumberFormatException ex) {
            System.out.printf("%nInvalid entry: %s%n%n", ex.getMessage());
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
