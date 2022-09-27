package com.ar.villaf.backendCourseManagmentSystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;
}
