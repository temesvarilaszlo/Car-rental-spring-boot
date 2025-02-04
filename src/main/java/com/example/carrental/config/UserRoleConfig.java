package com.example.carrental.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user")
public class UserRoleConfig {
    private String isAdmin;

    public boolean isAdmin() {
        return isAdmin.equals("true");
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
}
