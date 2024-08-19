package com.vlad.interview.config;

import com.vlad.interview.config.props.ApplicationProps;
import com.vlad.interview.repositories.IUsersRepository;
import com.vlad.interview.repositories.UsersRepositoryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final ApplicationProps applicationProps;

    @Bean
    public List<IUsersRepository> usersRepositories() {
        return applicationProps.getDataSources().stream()
                .map(UsersRepositoryFactory::createUsersRepository)
                .collect(Collectors.toList());
    }


}
