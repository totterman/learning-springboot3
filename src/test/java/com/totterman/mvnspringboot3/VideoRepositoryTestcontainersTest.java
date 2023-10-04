package com.totterman.mvnspringboot3;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ContextConfiguration(initializers = VideoRepositoryTestcontainersTest.DataSourceInitializer.class)
public class VideoRepositoryTestcontainersTest {

    @Autowired
    VideoRepository repository;

    @Container
    static final PostgreSQLContainer<?> database =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withUsername("postgres");

    @BeforeAll
    static void beforeAll() {
        database.start();
    }

    @AfterAll
    static void afterAll() {
        database.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

/*
    static class DataSourceInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils
                    .addInlinedPropertiesToEnvironment(applicationContext,
                            "spring.datasource.url=" + database.getJdbcUrl(),
                            "spring.datasource.username=" + database.getUsername(),
                            "spring.datasource.password=" + database.getPassword(),
                            "spring.jpa.hibernate.ddl-auto=create-drop");
        }
    }
*/
    @BeforeEach
    void setUp() {
        repository.saveAll(
                List.of(
                        new VideoEntity("Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up.",
                                "alice"),
                        new VideoEntity("Don't do THIS to your own CODE!",
                                "As a pro developer, never ever EVER do this to your code.",
                                "alice"),
                        new VideoEntity("SECRETS to fix BROKEN CODE!",
                                "Discover ways to not only debug your code",
                                "bob")));
    }

    @Test
    public void findAll() {
        final List<VideoEntity> videos = repository.findAll();
        assertThat(videos).hasSize(3);
    }

    @Test
    public void findByName() {
        final List<VideoEntity> videos = repository.findByNameContainsIgnoreCase("SPRiNG BOOT 3");
        assertThat(videos).hasSize(1);
    }

    @Test
    public void findByNameOrDescription() {
        final List<VideoEntity> videos = repository
                .findByNameContainsOrDescriptionContainsAllIgnoreCase("CODE", "YOUR CODE");
        assertThat(videos).hasSize(2);
    }
}
