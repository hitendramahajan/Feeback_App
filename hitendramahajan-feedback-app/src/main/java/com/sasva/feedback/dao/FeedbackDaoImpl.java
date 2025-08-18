package com.sasva.feedback.dao; 
 
import com.sasva.feedback.dto.FeedbackForm; 
import com.sasva.feedback.model.Feedback; 
import com.sasva.feedback.util.DatabaseConnectionUtil; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
 
import java.sql.*; 
import java.time.LocalDateTime; 
import java.util.ArrayList; 
import java.util.List; 
 
/** 
 * DAO implementation for feedback. 
 * 
 * <p> 
 * This class replaces the database access logic that was previously in Python's <code>db.py</code> and in route handlers. 
 * In the original Python Flask app, feedback was saved using direct SQL execution via a MySQL connector, e.g.: 
 * <pre> 
 * cursor.execute("INSERT INTO feedback (username, email, ...) VALUES (%s, %s, ...)", (form.username, ...)) 
 * </pre> 
 * In Java, we use JDBC with prepared statements and the DAO pattern for separation of concerns. 
 * </p> 
 * 
 * <p> 
 * <b>Major translation points:</b> 
 * <ul> 
 *   <li>Python's dynamic typing and dict-based models are replaced with Java POJOs (see Feedback.java).</li> 
 *   <li>Database connections are managed via try-with-resources for automatic cleanup.</li> 
 *   <li>SQL exceptions are caught and logged using SLF4J, and rethrown as unchecked exceptions for global handling.</li> 
 *   <li>Form data is passed as a FeedbackForm DTO, similar to WTForms' form object in Python.</li> 
 * </ul> 
 * </p> 
 */ 
public class FeedbackDaoImpl implements FeedbackDao { 
 
    private static final Logger logger = LoggerFactory.getLogger(FeedbackDaoImpl.class); 
 
    /** 
     * Saves feedback to the database. 
     * 
     * <p> 
     * Python equivalent: 
     * <pre> 
     * cursor.execute("INSERT INTO feedback (username, email, ...) VALUES (%s, %s, ...)", (form.username, ...)) 
     * </pre> 
     * Java translation uses JDBC and FeedbackForm DTO. 
     * </p> 
     * 
     * @param form the feedback form data to save 
     */ 
    @Override 
    public void saveFeedback(FeedbackForm form) { 
        String sql = "INSERT INTO feedback (username, email, phone, comments, star_rating, submitted_at) VALUES (?, ?, ?, ?, ?, ?)"; 
        try (Connection conn = DatabaseConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, form.getUsername()); 
            stmt.setString(2, form.getEmail()); 
            stmt.setString(3, form.getPhone()); 
            stmt.setString(4, form.getComments()); 
            stmt.setString(5, form.getStarRating()); 
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now())); 
            stmt.executeUpdate(); 
            logger.info("Feedback saved for user: {}", form.getUsername()); 
        } catch (SQLException e) { 
            logger.error("Failed to save feedback", e); 
            throw new RuntimeException("Database error", e); 
        } 
    } 
 
    /** 
     * Retrieves all feedback entries from the database. 
     * 
     * <p> 
     * Python equivalent: 
     * <pre> 
     * cursor.execute("SELECT * FROM feedback") 
     * feedbacks = cursor.fetchall() 
     * </pre> 
     * Java translation uses JDBC and maps ResultSet rows to Feedback POJOs. 
     * </p> 
     * 
     * @return list of feedback entries 
     */ 
    @Override 
    public List<Feedback> getAllFeedback() { 
        List<Feedback> feedbackList = new ArrayList<>(); 
        String sql = "SELECT * FROM feedback ORDER BY submitted_at DESC"; 
        try (Connection conn = DatabaseConnectionUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) { 
            while (rs.next()) { 
                Feedback feedback = new Feedback(); 
                feedback.setId(rs.getInt("id")); 
                feedback.setUsername(rs.getString("username")); 
                feedback.setEmail(rs.getString("email")); 
                feedback.setPhone(rs.getString("phone")); 
                feedback.setComments(rs.getString("comments")); 
                feedback.setStarRating(rs.getString("star_rating")); 
                Timestamp submittedAtTs = rs.getTimestamp("submitted_at"); 
                if (submittedAtTs != null) { 
                    feedback.setSubmittedAt(submittedAtTs.toLocalDateTime()); 
                } else { 
                    logger.warn("Null submitted_at for feedback id {}", rs.getInt("id")); 
                } 
                feedbackList.add(feedback); 
            } 
            logger.info("Fetched {} feedback entries from database.", feedbackList.size()); 
        } catch (SQLException e) { 
            logger.error("Failed to retrieve feedback list", e); 
            throw new RuntimeException("Database error", e); 
        } 
        return feedbackList; 
    } 
}