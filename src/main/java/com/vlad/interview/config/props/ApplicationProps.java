package com.vlad.interview.config.props;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProps {
    @NotEmpty
    private List<DataSourceProps> dataSources = new ArrayList<>();

    @Data
    public static class DataSourceProps {
        private Strategy strategy;
        private String name;
        private String url;
        private String driverClassName;
        private String user;
        private String password;
        private String table;
        private Map<String, String> mapping;
    }

    public enum Strategy {
        POSTGRES, MYSQL;
    }
}
