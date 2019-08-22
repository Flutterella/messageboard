package be.intecbrussel.messageboard.service;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    public byte[] hashPassword(String password, byte[] salt) throws AuthenticationException{

        char[] passwordChars = password.toCharArray();

        SecretKeyFactory skf = null;
        String finalPassword = "";
        try {
            skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException();
        }
        PBEKeySpec spec = new PBEKeySpec( passwordChars, salt, 10000, 56);
        SecretKey key = null;
        try {
            key = skf.generateSecret( spec );
        } catch (InvalidKeySpecException e) {
            throw new AuthenticationException();
        }
        byte[] hash = key.getEncoded();
        return hash;
    }

    public byte[] generateSalt(){
        Random random = new Random();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
