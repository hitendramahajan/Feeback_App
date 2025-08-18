package com.sasva.feedback.dao; 
 
import com.sasva.feedback.model.Feedback; 
import com.sasva.feedback.model.StarRating; 
import com.sasva.feedback.util.DatabaseConnectionUtil; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
 
import java.sql.*; 
import java.util.ArrayList; 
import java.util.List; 
 
@Repository 
public class FeedbackDaoImpl implements FeedbackDao { 
 
    private static final Logger logger = LoggerFactory.getLogger(FeedbackDaoImpl.class); 
 
    @Autowired 
    private DatabaseConnectionUtil dbUtil; 
 
    @Override 
    public boolean saveFeedback(Feedback feedback) { 
        String sql = "INSERT INTO feedback (username, email, phone, comments, rating, submitted_at) VALUES (?, ?, ?, ?, ?, ?)"; 
        try (Connection conn = dbUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, feedback.getUsername()); 
            stmt.setString(2, feedback.getEmail()); 
            stmt.setString(3, feedback.getPhone()); 
            stmt.setString(4, feedback.getComments()); 
            // Use StarRating enum's value for type safety 
            stmt.setInt(5, feedback.getStarRating().getValue()); 
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis())); 
            int affectedRows = stmt.executeUpdate(); 
            logger.info("Feedback saved for user: {}", feedback.getUsername()); 
            return affectedRows > 0; 
        } catch (SQLException e) { 
            logger.error("Error saving feedback: {}", e.getMessage(), e); 
            return false; 
        } 
    } 
 
    @Override 
    public List<Feedback> getAllFeedback() { 
        List<Feedback> feedbackList = new ArrayList<>(); 
        String sql = "SELECT * FROM feedback ORDER BY submitted_at DESC"; 
        try (Connection conn = dbUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) { 
            while (rs.next()) { 
                Feedback feedback = new Feedback(); 
                feedback.setId(rs.getInt("id")); 
                feedback.setUsername(rs.getString("username")); 
                feedback.setEmail(rs.getString("email")); 
                feedback.setPhone(rs.getString("phone")); 
                feedback.setComments(rs.getString("comments")); 
                // Map int rating from DB to StarRating enum 
                try { 
                    feedback.setStarRating(StarRating.fromValue(rs.getInt("rating"))); 
                } catch (IllegalArgumentException ex) { 
                    logger.error("Invalid star rating value {} in DB for feedback id {}: {}", rs.getInt("rating"), rs.getInt("id"), ex.getMessage(), ex); 
                    feedback.setStarRating(StarRating.ONE); // fallback to ONE or handle as needed 
                } 
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
            logger.error("Error retrieving feedback list: {}", e.getMessage(), e); 
        } 
        return feedbackList; 
    } 
}