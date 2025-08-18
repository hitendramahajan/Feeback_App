package com.sasva.feedback.exception; 
 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.ControllerAdvice; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
 
/** 
 * Global exception handler. 
 * 
 * <p> 
 * This class replaces Flask's <code>@app.errorhandler</code> mechanism from the original Python application. 
 * In Flask, you might see: 
 * <pre> 
 * @app.errorhandler(Exception) 
 * def handle_exception(e): 
 *     app.logger.error(str(e)) 
 *     return render_template('error.html') 
 * </pre> 
 * In Java Spring Boot, we use <code>@ControllerAdvice</code> and <code>@ExceptionHandler</code> to globally handle exceptions 
 * thrown by any controller. This ensures that all uncaught exceptions are logged and a user-friendly error page is shown. 
 * </p> 
 * 
 * <p> 
 * <b>Major translation points:</b> 
 * <ul> 
 *   <li>Python's dynamic exception handling is mapped to Java's static type system using <code>@ExceptionHandler(Exception.class)</code>.</li> 
 *   <li>Logging uses SLF4J instead of Python's <code>logging</code> module.</li> 
 *   <li>The error message is passed to the Thymeleaf template via the <code>Model</code> object, similar to Flask's <code>render_template</code> context.</li> 
 * </ul> 
 * </p> 
 * 
 * <pre> 
 * # Python (Flask) 
 * @app.errorhandler(Exception) 
 * def handle_exception(e): 
 *     app.logger.error(str(e)) 
 *     return render_template('error.html') 
 * 
 * // Java (Spring Boot) 
 * @ControllerAdvice 
 * public class GlobalExceptionHandler { 
 *     @ExceptionHandler(Exception.class) 
 *     public String handleException(Exception ex, Model model) { 
 *         logger.error("Unhandled exception", ex); 
 *         model.addAttribute("errorMessage", "An unexpected error occurred."); 
 *         return "error"; 
 *     } 
 * } 
 * </pre> 
 */ 
@ControllerAdvice 
public class GlobalExceptionHandler { 
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class); 
 
    /** 
     * Handles all uncaught exceptions thrown by controllers. 
     * <p> 
     * This method is the Java equivalent of Flask's <code>@app.errorhandler(Exception)</code>. 
     * It logs the full stack trace and returns the "error" view with a generic error message. 
     * </p> 
     * 
     * @param ex    the exception thrown 
     * @param model the Spring MVC model to pass data to the view 
     * @return the name of the error view template 
     */ 
    @ExceptionHandler(Exception.class) 
    public String handleException(Exception ex, Model model) { 
        // Log the full exception stack trace (Python: app.logger.error(str(e))) 
        logger.error("Unhandled exception", ex); 
        // Pass a user-friendly error message to the error page (Python: render_template('error.html')) 
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later."); 
        return "error"; 
    } 
}