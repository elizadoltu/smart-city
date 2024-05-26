package com.example.client;

public class User {
    private String id;
    private String hostname;
    private int port;

    public User(String id, String hostname, int port) {
        this.id = id;
        this.hostname = hostname;
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "User ID: " + id + ", Hostname: " + hostname + ", Port: " + port;
    }
}
