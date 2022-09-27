package com.ar.villaf.backendCourseManagmentSystem.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "video")
@Getter
public class VideoProperties {

    private String path;

}
