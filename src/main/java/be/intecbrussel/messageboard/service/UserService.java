package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.UserDto;
import be.intecbrussel.messageboard.model.User;

public interface UserService {

    public void registerUser(UserDto userDto) throws MismatchedPasswordsException;

    public User findUser(UserDto userDto);
}
