package com.ar.villaf.backendCourseManagmentSystem.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@ConfigurationProperties(prefix = "rsa") @Data
public class RsaKeyProperties {

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;
}
