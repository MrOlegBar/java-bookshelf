package ru.codeinside.dto;

import lombok.Getter;
import lombok.Setter;
import ru.codeinside.validator.annotation.PasswordMatches;
import ru.codeinside.validator.annotation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
@Getter
@Setter
public class UserDto {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}