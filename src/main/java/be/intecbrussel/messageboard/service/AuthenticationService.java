package be.intecbrussel.messageboard.service;

public interface AuthenticationService {

    public byte[] hashPassword(String password, byte[] salt) throws AuthenticationException;

    public byte[] generateSalt();
}
