/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI customOpenAPI(ApiProperties apiProperties) {
        return new OpenAPI()
                .info(new Info()
                        .title(String.format("%s Service API", StringUtils.capitalize(apiProperties.getAppName())))
                        .version(apiProperties.getAppVersion())
                        .description(apiProperties.getAppDescription())
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .email(apiProperties.getDeveloperEmail())
                                .name(apiProperties.getDeveloperName())
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        ));
    }
}
