package com.rsn.bankingsystem;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppState {
    private final Scanner scanner;
    private final BankCardDAO cardDao;
    private BankCard loggedInCard;

    public AppState(DBConnection dbConnection) {
        this.cardDao = new BankCardDAO(dbConnection);
        this.scanner = new Scanner(System.in);
    }

    public boolean tryLogin(String cardNumber, String pin) throws SQLException {
        BankCard card = this.cardDao.getCard(cardNumber, pin);
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

    public void addCard(BankCard card) throws SQLException {
        cardDao.insertCard(card);
    }
}
