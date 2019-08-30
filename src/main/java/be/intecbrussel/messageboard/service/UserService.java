package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.UserDto;
import be.intecbrussel.messageboard.model.User;

public interface UserService {

    void registerUser(UserDto userDto) throws MismatchedPasswordsException, DuplicateUserException;

    User findUser(UserDto userDto);
}
