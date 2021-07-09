package com.chocolatedevelopers.whistleblower.data.model;


public class User {
    private int userId;
    private int levelId;
    private String username;
    private String password;


    public User() {
    }

    public User(int userId, int levelId, String username, String password) {
        this.userId = userId;
        this.levelId = levelId;
        this.username = username;
        this.password = password;
    }

    public User(int levelId, String username, String password) {
        this.levelId = levelId;
        this.username = username;
        this.password = password;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
