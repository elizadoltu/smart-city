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
            // Drop tables if they exist
            String dropParkingSql = "DROP TABLE IF EXISTS parking;";
            String dropPowerstationsSql = "DROP TABLE IF EXISTS powerstations;";
            try (PreparedStatement pstmtDropParking = conn.prepareStatement(dropParkingSql);
                    PreparedStatement pstmtDropPowerstations = conn.prepareStatement(dropPowerstationsSql)) {
                pstmtDropParking.executeUpdate();
                pstmtDropPowerstations.executeUpdate();
                System.out.println("Tables 'parking' and 'powerstations' dropped if they existed.");
            }

            // Create tables
            String createParkingSql = "CREATE TABLE IF NOT EXISTS parking (\n"
                    + " name TEXT PRIMARY KEY,\n"
                    + " available INTEGER NOT NULL\n"
                    + ");";
            String createPowerstationsSql = "CREATE TABLE IF NOT EXISTS powerstations (\n"
                    + " name TEXT PRIMARY KEY,\n"
                    + " available INTEGER NOT NULL\n"
                    + ");";
            try (PreparedStatement pstmtCreateParking = conn.prepareStatement(createParkingSql);
                    PreparedStatement pstmtCreatePowerstations = conn.prepareStatement(createPowerstationsSql)) {
                pstmtCreateParking.executeUpdate();
                pstmtCreatePowerstations.executeUpdate();
                System.out.println("Tables 'parking' and 'powerstations' created.");
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

    public static void insertInitialData() {
        insertParkingLot("Primaria Iasi", 20);
        insertParkingLot("Palas Mall", 15);
        insertParkingLot("Bulevardul Independentei", 15);
        insertParkingLot("Hotel Unirea", 21);
        insertParkingLot("Ciric", 18);
        insertParkingLot("Aeroport Iasi", 12);

        insertPowerStation("Lukoil Pacurari", 10);
        insertPowerStation("OMW Silvestru", 2);
        insertPowerStation("Kaufland Chimiei", 6);
        insertPowerStation("Mol Nicolina", 5);
        insertPowerStation("Lidl Moara de foc", 3);
        insertPowerStation("Iulius Mall", 7);

        System.out.println("Initial data inserted into database");
    }

    public static void main(String[] args) {
        initializeDatabase();
        insertInitialData();

        List<String> parkingLots = getParkingLots();
        System.out.println("Parking lots in the database: " + parkingLots);

        List<String> powerStations = getPowerStations();
        System.out.println("Power station in the database: " + powerStations);
    }
}