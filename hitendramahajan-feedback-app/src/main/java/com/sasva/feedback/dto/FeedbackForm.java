package com.sasva.feedback.dto; 
 
import jakarta.validation.constraints.Email; 
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.NotNull; 
import jakarta.validation.constraints.Pattern; 
import jakarta.validation.constraints.Size; 
import com.sasva.feedback.model.StarRating; 
import com.sasva.feedback.util.AppConstants; 
 
/** 
 * FeedbackForm DTO for capturing and validating user feedback input. 
 * Uses Jakarta Bean Validation annotations to enforce field constraints. 
 */ 
public class FeedbackForm { 
 
    @NotBlank(message = "Username is required") 
    @Size(max = AppConstants.USERNAME_MAX_LENGTH, message = "Username must be at most " + AppConstants.USERNAME_MAX_LENGTH + " characters") 
    private String username; 
 
    @NotBlank(message = "Email is required") 
    @Email(message = "Please provide a valid email address") 
    @Size(max = AppConstants.EMAIL_MAX_LENGTH, message = "Email must be at most " + AppConstants.EMAIL_MAX_LENGTH + " characters") 
    private String email; 
 
    @NotBlank(message = "Phone number is required") 
    @Pattern(regexp = "^[0-9\\-\\+]{9,15}$", message = "Phone must be a valid number (9-15 digits)") 
    @Size(max = AppConstants.PHONE_MAX_LENGTH, message = "Phone must be at most " + AppConstants.PHONE_MAX_LENGTH + " characters") 
    private String phone; 
 
    @NotBlank(message = "Comments are required") 
    @Size(max = AppConstants.COMMENTS_MAX_LENGTH, message = "Comments must be at most " + AppConstants.COMMENTS_MAX_LENGTH + " characters") 
    private String comments; 
 
    @NotNull(message = "Star rating is required") 
    private StarRating starRating; 
 
    public FeedbackForm() { 
        // Default constructor 
    } 
 
    public String getUsername() { 
        return username; 
    } 
    public void setUsername(String username) { 
        this.username = username != null ? username.trim() : null; 
    } 
 
    public String getEmail() { 
        return email; 
    } 
    public void setEmail(String email) { 
        this.email = email != null ? email.trim() : null; 
    } 
 
    public String getPhone() { 
        return phone; 
    } 
    public void setPhone(String phone) { 
        this.phone = phone != null ? phone.trim() : null; 
    } 
 
    public String getComments() { 
        return comments; 
    } 
    public void setComments(String comments) { 
        this.comments = comments != null ? comments.trim() : null; 
    } 
 
    public StarRating getStarRating() { 
        return starRating; 
    } 
    public void setStarRating(StarRating starRating) { 
        this.starRating = starRating; 
    } 
}