package com.example.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            // Drop tables if they exist
            String dropParkingSql = "DROP TABLE IF EXISTS parking;";
            String dropPowerstationsSql = "DROP TABLE IF EXISTS powerstations;";
            try (PreparedStatement pstmtDropParking = conn.prepareStatement(dropParkingSql);
                    PreparedStatement pstmtDropPowerstations = conn.prepareStatement(dropPowerstationsSql);) {
                pstmtDropParking.executeUpdate();
                pstmtDropPowerstations.executeUpdate();
                System.out.println("Tables 'parking' and 'powerstations' dropped if they existed.");
            }

            String createParkingSql = "CREATE TABLE IF NOT EXISTS parking (\n"
                    + " name TEXT PRIMARY KEY,\n"
                    + " available INTEGER NOT NULL\n"
                    + ");";
            String createPowerstationsSql = "CREATE TABLE IF NOT EXISTS powerstations (\n"
                    + " name TEXT PRIMARY KEY,\n"
                    + " available INTEGER NOT NULL\n"
                    + ");";
            String createUsersSql = "CREATE TABLE IF NOT EXISTS users (\n"
                    + " id TEXT PRIMARY KEY,\n"
                    + " hostname TEXT NOT NULL,\n"
                    + " port INTEGER NOT NULL\n"
                    + ");";
            try (PreparedStatement pstmtCreateParking = conn.prepareStatement(createParkingSql);
                    PreparedStatement pstmtCreatePowerstations = conn.prepareStatement(createPowerstationsSql);
                    PreparedStatement pstmtCreateUsers = conn.prepareStatement(createUsersSql)) {
                pstmtCreateParking.executeUpdate();
                pstmtCreatePowerstations.executeUpdate();
                pstmtCreateUsers.executeUpdate();
                System.out.println("Tables 'parking', 'powerstations', and 'users' created.");
            }
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    public static List<String> getParkingLots() {
        List<String> parkingLots = new ArrayList<>();
        String sql = "SELECT name, available FROM parking";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                parkingLots.add(rs.getString("name") + " - Available: " + rs.getInt("available"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving parking lots from database: " + e.getMessage());
        }
        return parkingLots;
    }

    public static List<String> getPowerStations() {
        List<String> powerStations = new ArrayList<>();
        String sql = "SELECT name, available FROM powerstations";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                powerStations.add(rs.getString("name") + " - Available: " + rs.getInt("available"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving power stations from database: " + e.getMessage());
        }
        return powerStations;
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, hostname, port FROM users";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getString("id"), rs.getString("hostname"), rs.getInt("port")));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users from database: " + e.getMessage());
        }
        return users;
    }

    public static void insertParkingLot(String name, int available) {
        String sql = "INSERT INTO parking(name, available) VALUES(?, ?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, available);
            pstmt.executeUpdate();
            System.out.println("Parking lot '" + name + "' inserted into database.");
        } catch (SQLException e) {
            System.out.println("Error inserting parking lot into database: " + e.getMessage());
        }
    }

    public static void insertPowerStation(String name, int available) {
        String sql = "INSERT INTO powerstations(name, available) VALUES(?, ?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, available);
            pstmt.executeUpdate();
            System.out.println("Power station '" + name + "' inserted into database.");
        } catch (SQLException e) {
            System.out.println("Error inserting power station into database: " + e.getMessage());
        }
    }

    public static void insertUser(String hostname, int port) {
        String sql = "INSERT INTO users(id, hostname, port) VALUES(?, ?, ?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Generate a random user ID
            String userId = generateRandomId();
            pstmt.setString(1, userId);
            pstmt.setString(2, hostname);
            pstmt.setInt(3, port);
            pstmt.executeUpdate();
            System.out.println("User with ID '" + userId + "', hostname '" + hostname + "', and port '" + port
                    + "' inserted into database.");
        } catch (SQLException e) {
            System.out.println("Error inserting user into database: " + e.getMessage());
        }
    }

    private static String generateRandomId() {
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static void insertInitialData() {
        insertParkingLot("Palas Mall", 10);
        insertParkingLot("Primaria Iasi", 8);
        insertParkingLot("Sfantu Spiridon", 15);
        insertParkingLot("Hotel Unirea", 10);
        insertParkingLot("Ciric", 18);
        insertParkingLot("Aeroport Iasi", 20);

        insertPowerStation("Lidl Chimiei", 4);
        insertPowerStation("Lukoil Pacurari", 2);
        insertPowerStation("Iulius Mall", 6);
        insertPowerStation("OWV Pacurari", 4);
        insertPowerStation("Mol Silvestru", 2);
        insertPowerStation("Kaufland Alexandru Cel Bun", 6);
        System.out.println("Initial data inserted into database.");
    }

    public static void main(String[] args) {
        initializeDatabase();
        insertInitialData();

        List<String> parkingLots = getParkingLots();
        System.out.println("Parking lots in the database: " + parkingLots);

        List<String> powerStations = getPowerStations();
        System.out.println("Power stations in the database: " + powerStations);

        List<User> users = getUsers();
        System.out.println("Users in the database: " + users);
    }
}
