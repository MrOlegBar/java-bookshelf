package ru.codeinside.dto;

import lombok.Getter;
import lombok.Setter;
import ru.codeinside.validator.annotation.ValidEmail;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ShortUserDto {
    @Size(min = 8, max = 256)
    private String password;
    @ValidEmail
    private String email;
}