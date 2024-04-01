package com.simplenotes.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@DirtiesContext
public abstract class IntegrationTestDatabase {
    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:16.2");

    @BeforeAll
    public static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    public static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.liquibase.url", container::getJdbcUrl);
    }


    @AfterAll
    public static void stopContainer() {
        container.stop();
    }
}
