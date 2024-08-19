package com.vlad.interview.repositories;


import com.vlad.interview.config.props.ApplicationProps;
import com.vlad.interview.controllers.requests.UsersListRequest;
import com.vlad.interview.domain.User;
import org.apache.commons.lang3.stream.LangCollectors;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class UsersRepository implements IUsersRepository {
    private static final String ID_FIELD_NAME = "id";
    private static final String USERNAME_FIELD_NAME = "username";
    private static final String NAME_FIELD_NAME = "name";
    private static final String SURNAME_FIELD_NAME = "surname";

    private final ApplicationProps.DataSourceProps dataSourceProps;
    private final JdbcTemplate jdbcTemplate;

    public UsersRepository(ApplicationProps.DataSourceProps dataSourceProps, DataSource dataSource) {
        this.dataSourceProps = dataSourceProps;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> listUsers(UsersListRequest usersListRequest) {

        var selectFields = dataSourceProps.getMapping().entrySet().stream()
                .map(mapping -> "%s as %s".formatted(mapping.getValue(), mapping.getKey()))
                .collect(Collectors.joining(", "));
        var tableName = dataSourceProps.getTable();
        var baseQuery = "select %s from %s".formatted(selectFields, tableName);

        // build conditions
        var conditions = new ArrayList<>();
        usersListRequest.getSearch()
                .map(search -> getSearchStrategy(dataSourceProps.getStrategy()).formatted(
                        dataSourceProps.getMapping().get(NAME_FIELD_NAME),
                        dataSourceProps.getMapping().get(SURNAME_FIELD_NAME),
                        search))
                .ifPresent(conditions::add);

        // build final SQL
        var sql = conditions.isEmpty()
                ? baseQuery
                : baseQuery + " where %s".formatted(conditions.stream().collect(LangCollectors.joining(" ")));

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> User.builder()
                        .id(resultSet.getString(ID_FIELD_NAME))
                        .username(resultSet.getString(USERNAME_FIELD_NAME))
                        .name(resultSet.getString(NAME_FIELD_NAME))
                        .surname(resultSet.getString(SURNAME_FIELD_NAME))
                        .build());
    }

    private static String getSearchStrategy(ApplicationProps.Strategy strategy) {
        return switch (strategy) {
            case POSTGRES -> "%1$s ILIKE '%%%3$s%%' OR %2$s ILIKE '%%%3$s%%'";
            case MYSQL -> "LOWER(%1$s) LIKE LOWER('%%%3$s%%') OR LOWER(%2$s) LIKE LOWER('%%%3$s%%')";
        };

    }

}
