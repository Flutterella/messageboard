package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.controller.UserDto;
import be.intecbrussel.messageboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    ReadWriteLock lock = new ReentrantReadWriteLock();

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
            user.setPassword(userDto.getPassword());
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
