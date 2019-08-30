package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.UserDto;
import be.intecbrussel.messageboard.model.Role;
import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.repository.RoleRepository;
import be.intecbrussel.messageboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void registerUser(UserDto userDto) throws MismatchedPasswordsException, DuplicateUserException {
        if(!userDto.getPassword().equals(userDto.getMatchingPassword())){
            throw new MismatchedPasswordsException();
        }
        User tempUser;
        try{
            lock.readLock().lock();
            tempUser = userRepository.findByUsername(userDto.getUsername());
        }
        finally{
            lock.readLock().unlock();
        }
        if(tempUser == null){
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            Role role = roleRepository.findByName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            try{
                lock.writeLock().lock();
                userRepository.save(user);
            }
            finally{
                lock.writeLock().unlock();
            }
        }
        else{
            throw new DuplicateUserException();
        }
    }

    @Override
    public User findUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        return user;
    }
}