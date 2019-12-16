package com.example.whowantstobeamillionaire;

public class Player {
    private String username;
    private String password;
    private int totalEarnings;
    private int tempEarnings;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        totalEarnings = 0;
        tempEarnings = 0;
    }

    public Player() {

    }

    public Player(Player value) {
        username = value.username;
        password = value.password;
        totalEarnings = value.totalEarnings;
        tempEarnings = value.tempEarnings;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public int getTempEarnings() {
        return tempEarnings;
    }
}
