package com.comparus.interview.containers;

public class MySQLContainer extends org.testcontainers.containers.MySQLContainer<MySQLContainer> {
    private static final String IMAGE_VERSION = "mysql:latest";
    private static MySQLContainer container;

    private MySQLContainer() {
        super(IMAGE_VERSION);
    }

    public static MySQLContainer getInstance() {
        if (container == null) {
            container = new MySQLContainer();
            container.withInitScript("db/migration/data-base-2/test-data.sql");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_2_URL", container.getJdbcUrl());
        System.setProperty("DB_2_USERNAME", container.getUsername());
        System.setProperty("DB_2_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}