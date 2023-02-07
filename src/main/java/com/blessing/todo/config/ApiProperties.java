package com.blessing.todo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ApiProperties {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.description}")
    private String appDescription;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.developer.email}")
    private String developerEmail;

    @Value("${app.developer.name}")
    private String developerName;
}
