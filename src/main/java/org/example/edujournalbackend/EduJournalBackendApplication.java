package org.example.edujournalbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class EduJournalBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduJournalBackendApplication.class, args);
    }
}