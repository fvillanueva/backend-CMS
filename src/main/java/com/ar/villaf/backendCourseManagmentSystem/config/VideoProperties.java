package com.ar.villaf.backendCourseManagmentSystem.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "video")
@Data
public class VideoProperties {

    private String path;

}
