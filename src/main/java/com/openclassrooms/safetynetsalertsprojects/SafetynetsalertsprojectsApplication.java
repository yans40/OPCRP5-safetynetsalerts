package com.openclassrooms.safetynetsalertsprojects;

import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SafetynetsalertsprojectsApplication {
    private static final Logger logger = LogManager.getLogger("main");

    public static void main(String[] args) {
        logger.info("Initializing application");
        SpringApplication.run(SafetynetsalertsprojectsApplication.class, args);
    }

}
