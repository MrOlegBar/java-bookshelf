package ru.codeinside.dto;

import lombok.Getter;
import lombok.Setter;
import ru.codeinside.validator.annotation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShortUserDto {
    @NotNull
    @NotEmpty
    private String password;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}