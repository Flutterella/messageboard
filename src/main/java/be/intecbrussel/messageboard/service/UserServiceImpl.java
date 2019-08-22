package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.UserDto;
import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Random random = new Random();

    @Override
    public void registerUser(UserDto userDto) throws DuplicateUserException, AuthenticationException {
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

            byte[] salt = authenticationService.generateSalt();
            byte[] password = null;

            password = authenticationService.hashPassword(userDto.getPassword(), salt);

            user.setPassword(password);
            user.setSalt(salt);

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
    public void loginUser(UserDto userDto) throws InvalidLoginException, AuthenticationException {
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
            byte[] hashedPassword = authenticationService.hashPassword(userDto.getPassword(), tempUser.getSalt());
            if(Arrays.equals(hashedPassword, tempUser.getPassword())){
                return;
            }
            else{
                throw new InvalidLoginException();
            }
        }
    }
}
