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
import ru.codeinside.dto.UserDto;
import ru.codeinside.error.UserAlreadyExistException;
import ru.codeinside.service.ICaptchaService;
import ru.codeinside.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {
    private final IUserService iUserService;
    private final ICaptchaService iCaptchaService;

    public RegistrationController(IUserService iUserService, ICaptchaService iCaptchaService) {
        this.iUserService = iUserService;
        this.iCaptchaService = iCaptchaService;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@Valid @ModelAttribute UserDto userDto,
                                            BindingResult result, ModelAndView mav, HttpServletRequest request) {
        String response = request.getParameter("g-recaptcha-response");
        iCaptchaService.processResponse(response, request);

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
}