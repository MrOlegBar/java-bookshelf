package ru.codeinside.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "google.recaptcha.key")
@Component
public class CaptchaSettings {
    private String site;
    private String secret;
}