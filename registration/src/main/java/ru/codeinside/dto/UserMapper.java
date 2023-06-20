package ru.codeinside.dto;

import org.mapstruct.Mapper;
import ru.codeinside.model.User;

import java.util.Collection;
import java.util.List;

@Mapper
public abstract class UserMapper {
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(user.getEmail());
        return user;
    }

    public abstract List<UserDto> toUserDto(
            Collection<User> transactions);
}
