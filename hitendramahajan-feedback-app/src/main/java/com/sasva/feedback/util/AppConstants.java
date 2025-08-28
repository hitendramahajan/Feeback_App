package com.sasva.feedback.util; 
 
/** 
 * Application-wide constants. 
 * 
 * <p> 
 * In the original Python application, constants such as field length limits or status messages 
 * would typically be defined at the module level, e.g.: 
 * 
 * <pre> 
 * USERNAME_MAX_LENGTH = 50 
 * EMAIL_MAX_LENGTH = 100 
 * </pre> 
 * 
 * In Java, it is idiomatic to collect such constants in a dedicated class with <code>public static final</code> fields. 
 * This approach provides type safety, namespacing, and prevents accidental modification. 
 * </p> 
 * 
 * <p> 
 * <b>Example translation:</b> 
 * <pre> 
 * # Python 
 * USERNAME_MAX_LENGTH = 50 
 * 
 * // Java 
 * public static final int USERNAME_MAX_LENGTH = 50; 
 * </pre> 
 * </p> 
 * 
 * <p> 
 * This class is used throughout the application to enforce field length limits and to provide 
 * application-wide status messages, mirroring the use of constants in the Python codebase. 
 * </p> 
 */ 
public final class AppConstants { 
 
    // Field length limits (mirrors Python module-level constants) 
    public static final int USERNAME_MAX_LENGTH = 50; 
    public static final int EMAIL_MAX_LENGTH = 100; 
    public static final int PHONE_MAX_LENGTH = 20; 
    public static final int COMMENTS_MAX_LENGTH = 500; 
 
    // Application status messages (used for user feedback and error handling) 
    public static final String FEEDBACK_SUBMITTED_MSG = "Feedback submitted successfully!"; 
    public static final String ERROR_MSG = "An error occurred. Please try again."; 
 
    /** 
     * Private constructor to prevent instantiation. 
     * <p> 
     * In Python, modules cannot be instantiated, but in Java, we explicitly prevent instantiation 
     * of utility classes by making the constructor private. 
     * </p> 
     */ 
    private AppConstants() { 
        // Prevent instantiation 
    } 
}