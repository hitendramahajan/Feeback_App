package com.sasva.feedback; 
 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
 
/** 
 * Application entry point. 
 * 
 * <p> 
 * This class is the equivalent of Python's <code>app.py</code> in the original Flask application. 
 * In Flask, the application is created and run via <code>app = Flask(__name__)</code> and <code>app.run()</code>. 
 * In Spring Boot, the application is launched using <code>SpringApplication.run()</code>. 
 * </p> 
 * 
 * <pre> 
 * # Python (Flask) 
 * if __name__ == '__main__': 
 *     app.run() 
 * 
 * // Java (Spring Boot) 
 * public static void main(String[] args) { 
 *     SpringApplication.run(...); 
 * } 
 * </pre> 
 */ 
@SpringBootApplication 
public class HitendramahajanFeedbackAppApplication { 
 
    public static void main(String[] args) { 
        SpringApplication.run(HitendramahajanFeedbackAppApplication.class, args); 
    } 
}