package com.sasva.feedback.exception; 
 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.ControllerAdvice; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
 
@ControllerAdvice 
public class GlobalExceptionHandler { 
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class); 
 
    @ExceptionHandler(Exception.class) 
    public String handleException(Exception ex, Model model) { 
        logger.error("Unhandled exception occurred: {}", ex.getMessage(), ex); 
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later."); 
        return "error"; 
    } 
} 