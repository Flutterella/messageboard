package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.controller.UserDto;
import be.intecbrussel.messageboard.repository.UserRepository;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Random random = new Random();

    @Override
    public void registerUser(UserDto userDto) throws DuplicateUserException {
        List<User> users;
        try{
            lock.readLock().lock();
            users = userRepository.findUsersByUserName(userDto.getUserName());
        }
        finally{
            lock.readLock().unlock();
        }
        if(users.size() > 0){
            throw new DuplicateUserException();
        }
        else{
            User user = new User();
            user.setUserName(userDto.getUserName());

            String password = userDto.getPassword();

            char[] passwordChars = password.toCharArray();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            SecretKeyFactory skf = null;
            String finalPassword = "";
            try {
                skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
                PBEKeySpec spec = new PBEKeySpec( passwordChars, salt, 10000, 56);
                SecretKey key = skf.generateSecret( spec );
                byte[] hash = key.getEncoded( );
                String hashedString = Hex.encodeHexString(hash);
                String saltString = new String(salt);
                finalPassword = saltString + hashedString;
            } catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            user.setPassword(finalPassword);
            try{
                lock.writeLock().lock();
                userRepository.save(user);
            }
            finally{
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    public void loginUser(UserDto userDto) throws InvalidLoginException {
        List<User> users;
        try{
            lock.readLock().lock();
            users = userRepository.findUsersByUserName(userDto.getUserName());
        }
        finally{
            lock.readLock().unlock();
        }
        if(users.size() == 0){
            throw new InvalidLoginException();
        }
        else{
            User tempUser = users.get(0);
            //TODO: Add encryption here.
            if(userDto.getPassword().equals(tempUser.getPassword())){
                return;
            }
            else{
                throw new InvalidLoginException();
            }
        }
    }
}
