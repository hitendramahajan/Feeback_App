package com.sasva.feedback.util; 
 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
 
/** 
 * Utility for establishing database connections. 
 * 
 * <p> 
 * <b>Python mapping:</b> 
 * <pre> 
 * # db.py (Python) 
 * def get_db_connection(): 
 *     conn = mysql.connector.connect(...) 
 *     return conn 
 * </pre> 
 * In the original Python Flask app, database connections were managed via a helper function in <code>db.py</code>. 
 * In Java, we use a static utility method and JDBC for connection management. 
 * </p> 
 * 
 * <p> 
 * <b>Major translation points:</b> 
 * <ul> 
 *   <li>Python uses <code>mysql.connector.connect</code> with keyword arguments; Java uses <code>DriverManager.getConnection</code> with a JDBC URL.</li> 
 *   <li>Python returns a connection object; Java returns a <code>java.sql.Connection</code> object.</li> 
 *   <li>Python's exceptions are caught with <code>except Exception as e</code>; Java uses <code>try-catch</code> for <code>SQLException</code>.</li> 
 *   <li>In Java, resource management is typically handled with try-with-resources for automatic closing.</li> 
 *   <li>Connection details are hardcoded here for clarity, but in production should be externalized (e.g., <code>application.properties</code>).</li> 
 * </ul> 
 * </p> 
 * 
 * <p> 
 * <b>Example usage:</b> 
 * <pre> 
 * try (Connection conn = DatabaseConnectionUtil.getConnection()) { 
 *     // Use the connection 
 * } catch (SQLException e) { 
 *     // Handle error 
 * } 
 * </pre> 
 * </p> 
 * 
 * <p> 
 * <b>Architectural difference:</b> 
 * <ul> 
 *   <li>Python's dynamic typing and duck-typed connections are replaced by Java's static typing and checked exceptions.</li> 
 *   <li>Java encourages explicit error handling and logging at every step.</li> 
 * </ul> 
 * </p> 
 */ 
public class DatabaseConnectionUtil { 
 
    /** 
     * Obtain a new database connection. 
     * 
     * <p> 
     * <b>Python equivalent:</b> 
     * <pre> 
     * conn = mysql.connector.connect( 
     *     host="localhost", 
     *     user="root", 
     *     password="password", 
     *     database="feedbackdb" 
     * ) 
     * </pre> 
     * <b>Java:</b> 
     * <pre> 
     * Connection conn = DriverManager.getConnection(url, user, password); 
     * </pre> 
     * </p> 
     * 
     * @return a new JDBC Connection to the feedback database 
     * @throws SQLException if a database access error occurs 
     */ 
    public static Connection getConnection() throws SQLException { 
        // In production, these should be externalized to application.properties or environment variables. 
        // INPUT_REQUIRED {Set your MySQL JDBC URL, username, and password below} 
        String url = "jdbc:mysql://localhost:3306/feedbackdb"; // INPUT_REQUIRED {JDBC URL for your MySQL database} 
        String user = "root"; // INPUT_REQUIRED {MySQL username} 
        String password = "password"; // INPUT_REQUIRED {MySQL password} 
 
        try { 
            // Logging is important for debugging connection issues. 
            System.out.println("Attempting to connect to database: " + url); 
            Connection conn = DriverManager.getConnection(url, user, password); 
            System.out.println("Database connection established successfully."); 
            return conn; 
        } catch (SQLException e) { 
            // Log the full stack trace for troubleshooting. 
            System.err.println("Failed to establish database connection: " + e.getMessage()); 
            e.printStackTrace(); 
            throw e; 
        } 
    } 
}