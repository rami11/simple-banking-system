package com.rsn.bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankCardDAO {

    private final DBConnection connection;

    public BankCardDAO(DBConnection connection) {
        this.connection = connection;
    }

    public void insertCard(BankCard card) throws SQLException {
        String query = " INSERT INTO card (number, pin) VALUES (?, ?) ";

        try (Connection conn = this.connection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, card.getCardNumber());
            statement.setString(2, card.getPin());
            statement.executeUpdate();
        }
    }
}
