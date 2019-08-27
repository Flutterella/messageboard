package be.intecbrussel.messageboard.controller;

public class UserDto {

    private String username;
    private String password;
    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public UserDto setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
        return this;
    }
}