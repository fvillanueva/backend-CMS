package com.ar.villaf.backendCourseManagmentSystem.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration @Getter
public class CustomProperties {

    @Value("${videos.path}")
    private String videosPath;

}
