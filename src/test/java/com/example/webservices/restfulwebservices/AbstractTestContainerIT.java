package com.example.webservices.restfulwebservices;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = AbstractTestContainerIT.Initializer.class)
@Testcontainers
public abstract class AbstractTestContainerIT {

    public static final PostgreSQLContainer db;
    private static final String DOCKER_IMAGE_NAME = "postgres:15.2-alpine";

    static {
        db = new PostgreSQLContainer(DOCKER_IMAGE_NAME);
        db.start();

        JdbcDatabaseDelegate jdbcDatabaseDelegate = new JdbcDatabaseDelegate(db, "");
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "data/users.sql");
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "data/posts.sql");
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    configurableApplicationContext,
                    "spring.datasource.url=" + db.getJdbcUrl(),
                    "spring.datasource.username=" + db.getUsername(),
                    "spring.datasource.password=" + db.getPassword()
            );
        }
    }
}
