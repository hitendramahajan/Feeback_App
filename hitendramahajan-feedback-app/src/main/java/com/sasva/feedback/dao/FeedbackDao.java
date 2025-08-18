package com.sasva.feedback.dao; 
 
import com.sasva.feedback.model.Feedback; 
 
import java.util.List; 
 
public interface FeedbackDao { 
    void saveFeedback(Feedback feedback) throws Exception; 
    List<Feedback> getAllFeedback() throws Exception; 
} 