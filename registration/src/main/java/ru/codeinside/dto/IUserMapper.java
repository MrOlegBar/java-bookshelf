package ru.codeinside.dto;

import org.mapstruct.Mapper;
import ru.codeinside.model.User;

@Mapper
public interface IUserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}