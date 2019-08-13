package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.model.UserDto;

public interface UserService {

    public void registerUser(UserDto UserDto) throws DuplicateUserException;

    public void loginUser(UserDto UserDto) throws InvalidLoginException;
}
