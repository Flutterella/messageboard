package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.model.UserDto;
import be.intecbrussel.messageboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(UserDto userDto) throws DuplicateUserException {
        List<User> users = userRepository.findUsersByUserName(userDto.getUserName());
        if(users.size() > 0){
            throw new DuplicateUserException();
        }
        else{
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
        }
    }
}
