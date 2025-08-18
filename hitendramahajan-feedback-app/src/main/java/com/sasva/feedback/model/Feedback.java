package com.sasva.feedback.model; 
 
import java.time.LocalDateTime; 
import com.sasva.feedback.model.StarRating; 
 
public class Feedback { 
    private int id; 
    private String username; 
    private String email; 
    private String phone; 
    private String comments; 
    private StarRating starRating; 
    private LocalDateTime submittedAt; 
 
    public Feedback() {} 
 
    public Feedback(int id, String username, String email, String phone, String comments, StarRating starRating, LocalDateTime submittedAt) { 
        this.id = id; 
        this.username = username; 
        this.email = email; 
        this.phone = phone; 
        this.comments = comments; 
        this.starRating = starRating; 
        this.submittedAt = submittedAt; 
    } 
 
    public int getId() { return id; } 
    public void setId(int id) { this.id = id; } 
 
    public String getUsername() { return username; } 
    public void setUsername(String username) { this.username = username; } 
 
    public String getEmail() { return email; } 
    public void setEmail(String email) { this.email = email; } 
 
    public String getPhone() { return phone; } 
    public void setPhone(String phone) { this.phone = phone; } 
 
    public String getComments() { return comments; } 
    public void setComments(String comments) { this.comments = comments; } 
 
    public StarRating getStarRating() { return starRating; } 
    public void setStarRating(StarRating starRating) { this.starRating = starRating; } 
 
    public LocalDateTime getSubmittedAt() { return submittedAt; } 
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; } 
}