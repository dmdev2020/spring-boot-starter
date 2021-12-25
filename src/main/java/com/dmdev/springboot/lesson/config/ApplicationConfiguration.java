package com.dmdev.springboot.lesson.config;

import com.dmdev.springboot.lesson.conditional.FirstConditional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Conditional(FirstConditional.class)
@Profile("dev")
@Configuration
public class ApplicationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @PostConstruct
    public void init() {
        log.warn("app is loaded!!!");
    }
}
