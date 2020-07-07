package com.rsn.bankingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankCardDAO {

    private final DBConnection connection;

    public BankCardDAO(DBConnection connection) {
        this.connection = connection;
    }

    public BankCard getCard(String number, String pin) throws SQLException {
        String query = " SELECT * FROM card WHERE number = ? AND pin = ? LIMIT 1";
        try (Connection conn = this.connection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, number);
            statement.setString(2, pin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int balance = resultSet.getInt("balance");
                return new BankCard(id, number, pin, balance);
            }
            return null;
        }
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
