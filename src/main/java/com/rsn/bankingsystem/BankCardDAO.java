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

    public void transferIncome(int fromId, int toId, int income) throws SQLException {
        String toQuery = " UPDATE card SET balance =  balance + ? WHERE id = ? ";
        String fromQuery = " UPDATE card SET balance =  balance - ? WHERE id = ? ";
        try (Connection conn = this.connection.connect();
             PreparedStatement toStatement = conn.prepareStatement(toQuery);
             PreparedStatement fromStatement = conn.prepareStatement(fromQuery)) {

            fromStatement.setInt(1, income);
            fromStatement.setInt(2, fromId);
            fromStatement.executeUpdate();

            toStatement.setInt(1, income);
            toStatement.setInt(2, toId);
            toStatement.executeUpdate();
        }
    }

    public void addIncome(int id, int income) throws SQLException {
        String query = " UPDATE card SET balance =  balance + ? WHERE id = ? ";
        try (Connection conn = this.connection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, income);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public int getBalance(int id) throws SQLException {
        String query = " SELECT balance FROM card WHERE id = ? LIMIT 1";
        try (Connection conn = this.connection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("balance");
            }
            throw new SQLException();
        }
    }

    public int getCardId(String cardNumber) throws SQLException {
        String query = " SELECT id FROM card WHERE number = ? LIMIT 1";
        try (Connection conn = this.connection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        }
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
