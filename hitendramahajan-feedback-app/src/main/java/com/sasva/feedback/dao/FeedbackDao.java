package com.sasva.feedback.dao; 
 
import com.sasva.feedback.dto.FeedbackForm; 
import com.sasva.feedback.model.Feedback; 
 
import java.util.List; 
 
/** 
 * Data Access Object (DAO) interface for feedback. 
 * 
 * <p> 
 * In Python, database operations were performed directly in the route functions, 
 * often using a cursor object from a MySQL connection, e.g.: 
 * <pre> 
 * # Python (Flask) 
 * conn = get_db_connection() 
 * cursor = conn.cursor() 
 * cursor.execute("INSERT INTO feedback ...", (...)) 
 * conn.commit() 
 * </pre> 
 * In Java, we use the DAO pattern to separate data access logic from business logic. 
 * This interface defines the contract for feedback data operations. 
 * </p> 
 * 
 * <p> 
 * <b>Translation Example:</b> 
 * <pre> 
 * # Python (Flask) 
 * def save_feedback(form): 
 *     ... 
 * 
 * // Java (Spring Boot) 
 * void saveFeedback(FeedbackForm form); 
 * </pre> 
 * </p> 
 */ 
public interface FeedbackDao { 
    /** 
     * Persists a feedback form to the database. 
     * 
     * <p> 
     * Python equivalent: a function that inserts feedback data into MySQL. 
     * In Java, we use a method that takes a FeedbackForm DTO and handles JDBC logic in the implementation. 
     * </p> 
     * 
     * @param form the feedback form data to save 
     */ 
    void saveFeedback(FeedbackForm form); 
 
    /** 
     * Retrieves all feedback entries from the database. 
     * 
     * <p> 
     * Python equivalent: a function that fetches all feedback rows from MySQL and returns them as dicts. 
     * In Java, this method returns a list of Feedback POJOs. 
     * </p> 
     * 
     * @return list of feedback entries 
     */ 
    List<Feedback> getAllFeedback(); 
}