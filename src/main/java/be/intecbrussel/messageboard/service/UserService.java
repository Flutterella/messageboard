package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.UserDto;

public interface UserService {

    public void registerUser(UserDto UserDto) throws DuplicateUserException, AuthenticationException;

    public void loginUser(UserDto UserDto) throws InvalidLoginException, AuthenticationException;
}
