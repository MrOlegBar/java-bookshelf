package ru.codeinside.service;

import javax.servlet.http.HttpServletRequest;

public interface ICaptchaService {
    void processResponse(String response, HttpServletRequest request);
}