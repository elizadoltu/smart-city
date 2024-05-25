package com.example.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connected to SQLite database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return conn;
    }

    public static void initializeDatabase() {
        try (Connection conn = connect()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                    + " id INTEGER PRIMARY KEY,\n"
                    + " name TEXT NOT NULL\n"
                    + ");";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
            }
            System.out.println("Table 'users' created (if not existed).");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    public static List<String> getUsers() {
        List<String> users = new ArrayList<>();
        String sql = "SELECT name FROM users";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                users.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users from database: " + e.getMessage());
        }
        return users;
    }

    public static void insertUser(String name) {
        String sql = "INSERT INTO users(name) VALUES(?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("User '" + name + "' inserted into database.");
        } catch (SQLException e) {
            System.out.println("Error inserting user into database: " + e.getMessage());
        }
    }

    public static void insertInitialUsers() {
        insertUser("Alice");
        insertUser("Bob");
        insertUser("Charlie");
        System.out.println("Initial users inserted into database.");
    }
}
