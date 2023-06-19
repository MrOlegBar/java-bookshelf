package ru.codeinside.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.codeinside.dto.UserDto;
import ru.codeinside.error.UserAlreadyExistException;
import ru.codeinside.model.User;
import ru.codeinside.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException(String.format("There is an account with that email address: %s.",
                    userDto.getEmail()));
        }

        userRepository.save();
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
