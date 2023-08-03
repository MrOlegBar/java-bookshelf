package ru.codeinside.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.codeinside.dto.IUserMapper;
import ru.codeinside.dto.ShortUserDto;
import ru.codeinside.dto.UserDto;
import ru.codeinside.error.UserAlreadyExistException;
import ru.codeinside.error.UserNotFoundException;
import ru.codeinside.model.User;
import ru.codeinside.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IUserMapper iUserMapper;

    public UserService(UserRepository userRepository, IUserMapper iUserMapper) {
        this.userRepository = userRepository;
        this.iUserMapper = iUserMapper;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException(String.format("There is an account with that email address: %s.",
                    userDto.getEmail()));
        }

        User user = iUserMapper.toUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public Boolean checkingUserAccount(ShortUserDto shortUserDto) throws UserAlreadyExistException {
        if (!emailExists(shortUserDto.getEmail())) {
            throw new UserNotFoundException(String.format("There is not an account with that email address: %s.",
                    shortUserDto.getEmail()));
        } else if (!emailAndPasswordExists(shortUserDto.getEmail(), shortUserDto.getPassword())) {
            throw new UserNotFoundException(String.format("Incorrect password with that email address: %s.",
                    shortUserDto.getEmail()));
        }

        return true;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmailIgnoreCase(email) != null;
    }

    private boolean emailAndPasswordExists(String email, String password) {
        return userRepository.findByEmailIgnoreCaseAndPasswordEquals(email, password) != null;
    }
}