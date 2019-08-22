package be.intecbrussel.messageboard.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    @Lob
    private byte[] password;

    @Lob
    private byte[] salt;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public byte[] getPassword() {
        return password;
    }

    public User setPassword(byte[] password) {
        this.password = password;
        return this;
    }

    public byte[] getSalt() {
        return salt;
    }

    public User setSalt(byte[] salt) {
        this.salt = salt;
        return this;
    }
}
