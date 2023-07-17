package ru.codeinside.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import ru.codeinside.config.CaptchaSettings;
import ru.codeinside.config.GoogleResponse;
import ru.codeinside.error.InvalidReCaptchaException;
import ru.codeinside.error.ReCaptchaInvalidException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaptchaService implements ICaptchaService {

    private final CaptchaSettings captchaSettings;
    private final RestTemplate restTemplate;

    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public void processResponse(String response, HttpServletRequest request) {
        if(!responseSanityCheck(response)) {
            throw new InvalidReCaptchaException("Response contains invalid characters");
        }

        URI verifyUri = URI.create(String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                captchaSettings.getSecret(), response, request.getRemoteAddr()));

        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);

        if(!Objects.requireNonNull(googleResponse).isSuccess()) {
            throw new ReCaptchaInvalidException("reCaptcha was not successfully validated");
        }
    }

    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }
}