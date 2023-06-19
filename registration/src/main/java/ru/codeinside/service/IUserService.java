package ru.codeinside.service;

import ru.codeinside.dto.UserDto;
import ru.codeinside.error.UserAlreadyExistException;
import ru.codeinside.model.User;

public interface IUserService {
    User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
}
