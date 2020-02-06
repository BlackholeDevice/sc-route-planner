package com.blackholedevice.scrouteplanner.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServletPrefixConfiguration implements WebMvcConfigurer {
    private static final String PREFIX = "/api";

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(PREFIX, c -> c.isAnnotationPresent(Controller.class) || c.isAnnotationPresent(RestController.class));
    }
}
