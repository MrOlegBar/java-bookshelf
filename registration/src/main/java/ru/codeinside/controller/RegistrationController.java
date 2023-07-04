package ru.codeinside.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.codeinside.dto.UserDto;
import ru.codeinside.error.UserAlreadyExistException;
import ru.codeinside.error.UserNotFoundException;
import ru.codeinside.model.User;
import ru.codeinside.service.IUserService;

import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {
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

    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "login";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@Valid @ModelAttribute UserDto userDto,
                                            BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            mav.setViewName("registration");
            return mav;
        }

        try {
            User registeredUser = iUserService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that email already exists.");
            mav.setViewName("registration");
            return mav;
        }

        mav.setViewName("successRegister");
        return mav;
    }

    @PostMapping("/user/login")
    public ModelAndView loginUserAccount(@Valid @ModelAttribute UserDto userDto,
                                            BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            mav.setViewName("login");
            return mav;
        }

        try {
            Boolean loginedUser = iUserService.checkingUserAccount(userDto);
        } catch (UserNotFoundException unfEx) {
            mav.addObject("message", "User for that email not founds.");
            mav.setViewName("login");
            return mav;
        }

        mav.setViewName("successLogin");
        return mav;
    }
}