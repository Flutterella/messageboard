package be.intecbrussel.messageboard.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String email;

    public String getUserName() {
        return userName;
    }

    public UserDto setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
