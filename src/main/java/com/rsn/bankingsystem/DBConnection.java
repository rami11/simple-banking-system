package com.rsn.bankingsystem;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private final String url;

    public DBConnection(String fileName) {
        url = "jdbc:sqlite:" + fileName;
        initDB();
    }

    public Connection connect() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        return dataSource.getConnection();
    }

    private void initDB() {
        String query = " CREATE TABLE IF NOT EXISTS card ( " +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " number TEXT, " +
                " pin TEXT, " +
                " balance INTEGER DEFAULT 0 );";

        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
