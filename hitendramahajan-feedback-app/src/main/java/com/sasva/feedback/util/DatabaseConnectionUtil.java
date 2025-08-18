package com.sasva.feedback.util; 
 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component; 
 
import javax.annotation.PostConstruct; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
 
/** 
 * Utility class for managing MySQL database connections. 
 * Provides methods to obtain connections, with integrated logging and error handling. 
 */ 
@Component 
public class DatabaseConnectionUtil { 
 
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionUtil.class); 
 
    @Value("${spring.datasource.url}") 
    private String dbUrl; 
 
    @Value("${spring.datasource.username}") 
    private String dbUser; 
 
    @Value("${spring.datasource.password}") 
    private String dbPassword; 
 
    @PostConstruct 
    public void init() { 
        logger.info("DatabaseConnectionUtil initialized with URL: {}", dbUrl); 
    } 
 
    /** 
     * Get a database connection. 
     * @return Connection object 
     * @throws SQLException if unable to get connection 
     */ 
    public Connection getConnection() throws SQLException { 
        try { 
            logger.debug("Attempting to connect to database: {}", dbUrl); 
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword); 
            logger.info("Database connection established successfully."); 
            return conn; 
        } catch (SQLException e) { 
            logger.error("Failed to establish database connection: {}", e.getMessage(), e); 
            throw e; 
        } 
    } 
}