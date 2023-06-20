package ru.codeinside.dto;

import org.mapstruct.Mapper;
import ru.codeinside.model.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    public User toUser(UserDto userDto);
}