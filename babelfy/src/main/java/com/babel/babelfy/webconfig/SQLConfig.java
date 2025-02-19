package com.babel.babelfy.webconfig;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class SQLConfig {

    @Bean
    public ApplicationRunner run(JdbcTemplate jdbcTemplate) {
        return args -> {
            // Print a message before running the SQL script
            System.out.println("Running import.sql...");

            // Run the SQL script manually
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/import.sql")));
            jdbcTemplate.execute(sql);

            // Print message after running the script
            System.out.println("import.sql executed successfully.");
        };
    }
}
