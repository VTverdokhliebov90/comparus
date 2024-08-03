package com.comparus.interview.repositories;

import com.comparus.interview.config.props.ApplicationProps;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public final class UsersRepositoryFactory {
    private static final String FLYWAY_MIGRATION_PATH_TEMPLATE = "db/migration/%s";

    private UsersRepositoryFactory() {
        // Factory class
    }

    public static IUsersRepository createUsersRepository(ApplicationProps.DataSourceProps dataSourceProps) {
        var dataSource = createDataSource(dataSourceProps);
        applyFlywayMigration(dataSource, dataSourceProps.getName());
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

    private static void applyFlywayMigration(DataSource dataSource, String name) {
        var flywayConf = new ClassicConfiguration();
        flywayConf.setDataSource(dataSource);
        flywayConf.setLocations(new Location(FLYWAY_MIGRATION_PATH_TEMPLATE.formatted(name)));
        var flyway = new Flyway(flywayConf);
        flyway.migrate();
    }

}
