package com.ar.villaf.backendCourseManagmentSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {

    @Value("${videos.path}")
    private String videosPath;

    public String getVideosPath() {
        return videosPath;
    }
}
