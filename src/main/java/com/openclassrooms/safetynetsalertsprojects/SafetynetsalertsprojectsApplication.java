package com.openclassrooms.safetynetsalertsprojects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetynetsalertsprojectsApplication {
    private static final Logger logger = LogManager.getLogger("mainApp");
    public static void main(String[] args) {
        logger.info("Initializing application");
        SpringApplication.run(SafetynetsalertsprojectsApplication.class, args);
    }

}
