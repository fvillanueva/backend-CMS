package com.ar.villaf.backendCourseManagmentSystem.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "video")
@Data
public class VideoProperties {

    private String path;

}
