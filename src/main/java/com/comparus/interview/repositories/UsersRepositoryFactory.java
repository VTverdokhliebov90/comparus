package com.comparus.interview.repositories;

import com.comparus.interview.config.props.ApplicationProps;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public final class UsersRepositoryFactory {

    private UsersRepositoryFactory() {
        // Factory class
    }

    public static IUsersRepository createUsersRepository(ApplicationProps.DataSourceProps dataSourceProps) {
        var dataSource = createDataSource(dataSourceProps);
        return new UsersRepository(dataSourceProps, dataSource);
    }

    private static DataSource createDataSource(ApplicationProps.DataSourceProps props) {
        return DataSourceBuilder.create()
                .driverClassName(props.getDriverClassName())
                .url(props.getUrl())
                .username(props.getUser())
                .password(props.getPassword())
                .build();
    }

}
