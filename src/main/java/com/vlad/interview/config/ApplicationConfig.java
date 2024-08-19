package com.vlad.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ApplicationConfig {
    private static final String USERS_TASK_THREAD_NAME = "usersTaskExecutor-";

    @Bean(name = "usersTaskExecutor")
    public Executor usersThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 4);
        executor.setThreadNamePrefix(USERS_TASK_THREAD_NAME);
        executor.initialize();
        return executor;
    }
}
