package ru.codeinside.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.codeinside.dto.ShortUserDto;
import ru.codeinside.dto.UserDto;
import ru.codeinside.error.UserAlreadyExistException;
import ru.codeinside.error.UserNotFoundException;
import ru.codeinside.service.IUserService;

import javax.validation.Valid;

@Controller
@Slf4j
public class    RegistrationController {
    private final IUserService iUserService;

    public RegistrationController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@Valid @ModelAttribute UserDto userDto,
                                            BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            ObjectError objectError = result.getGlobalError();
            if (objectError != null) {
                mav.addObject("messageGlobalError", result.getGlobalError().getDefaultMessage());
            }
            mav.setViewName("registration");
            return mav;
        }

        try {
            iUserService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", uaeEx.getMessage());
            mav.setViewName("registration");
            return mav;
        }

        mav.setViewName("successRegister");
        return mav;
    }

    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        ShortUserDto shortUserDto = new ShortUserDto();
        model.addAttribute("shortUserDto", shortUserDto);
        return "login";
    }

    @PostMapping("/user/login")
    public ModelAndView loginUserAccount(@Valid @ModelAttribute ShortUserDto shortUserDto,
                                            BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            mav.setViewName("login");
            return mav;
        }

        try {
            iUserService.checkingUserAccount(shortUserDto);
        } catch (UserNotFoundException unfEx) {
            mav.addObject("message", unfEx.getMessage());
            mav.setViewName("login");
            return mav;
        }

        mav.setViewName("successLogin");
        return mav;
    }
}