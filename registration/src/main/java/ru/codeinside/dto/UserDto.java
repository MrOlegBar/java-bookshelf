package ru.codeinside.dto;

import lombok.Getter;
import lombok.Setter;
import ru.codeinside.validator.annotation.PasswordMatches;
import ru.codeinside.validator.annotation.ValidEmail;

import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches
public class UserDto {
    @Size(min = 2, max = 20)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;

    @Size(min = 8, max = 256)
    private String password;
    @Size(min = 8, max = 256)
    private String matchingPassword;

    @ValidEmail
    private String email;
}