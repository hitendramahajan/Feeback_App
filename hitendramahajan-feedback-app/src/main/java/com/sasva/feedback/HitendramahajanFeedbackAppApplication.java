package com.sasva.feedback; 
 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
 
/** 
 * Main application class for the Hitendramahajan Feedback App. 
 * This class bootstraps the Spring Boot application. 
 */ 
@SpringBootApplication 
public class HitendramahajanFeedbackAppApplication { 
 
    private static final Logger logger = LoggerFactory.getLogger(HitendramahajanFeedbackAppApplication.class); 
 
    public static void main(String[] args) { 
        try { 
            logger.info("Starting Hitendramahajan Feedback App Application..."); 
            SpringApplication.run(HitendramahajanFeedbackAppApplication.class, args); 
            logger.info("Application started successfully."); 
        } catch (Exception e) { 
            logger.error("Application failed to start.", e); 
            // Optionally, rethrow or exit 
            System.exit(1); 
        } 
    } 
} 