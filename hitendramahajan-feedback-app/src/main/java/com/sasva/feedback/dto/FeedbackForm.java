package com.sasva.feedback.dto; 
 
import jakarta.validation.constraints.Email; 
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.NotNull; 
import jakarta.validation.constraints.Pattern; 
import jakarta.validation.constraints.Size; 
import com.sasva.feedback.model.StarRating; 
import com.sasva.feedback.util.AppConstants; 
 
/** 
 * DTO for feedback form data. 
 * 
 * <p> 
 * <b>Python mapping:</b> 
 * <pre> 
 * # forms.py (Python/WTForms) 
 * class FeedbackForm(FlaskForm): 
 *     username = StringField('Username', validators=[DataRequired()]) 
 *     email = StringField('Email', validators=[DataRequired(), Email()]) 
 *     phone = StringField('Phone', validators=[DataRequired()]) 
 *     comments = TextAreaField('Comments', validators=[DataRequired()]) 
 *     star_rating = SelectField('Star Rating', coerce=int, choices=[...]) 
 * </pre> 
 * 
 * <b>Java translation:</b> 
 * <ul> 
 *   <li>Uses Jakarta Bean Validation annotations (e.g., @NotBlank, @Email, @Pattern, @Size) to enforce field constraints, which directly map to WTForms validators in Python.</li> 
 *   <li>Field names and types are preserved wherever possible for conceptual equivalence and easier migration.</li> 
 *   <li>Python's dynamic typing is replaced by Java's static typing and enum for <code>starRating</code> (see {@link StarRating}).</li> 
 *   <li>Validation messages are provided for user feedback, similar to WTForms' error messages.</li> 
 *   <li>Constants for max lengths are centralized in {@link AppConstants} for maintainability, as opposed to Python's use of magic numbers or module-level constants.</li> 
 * </ul> 
 * 
 * <b>Example translation:</b> 
 * <pre> 
 * # Python 
 * username = StringField('Username', validators=[DataRequired()]) 
 * 
 * // Java 
 * @NotBlank(message = "Username is required") 
 * @Size(max = AppConstants.USERNAME_MAX_LENGTH, message = "Username must be at most ...") 
 * private String username; 
 * </pre> 
 * 
 * <b>Architectural note:</b> 
 * In Python/Flask, form validation and data transfer are handled by WTForms classes. 
 * In Java/Spring Boot, this DTO is used as a @ModelAttribute in controller methods, and validation is triggered by @Valid. 
 * </p> 
 */ 
public class FeedbackForm { 
 
    /** 
     * Username of the feedback submitter. 
     * Python: username = StringField('Username', validators=[DataRequired()]) 
     */ 
    @NotBlank(message = "Username is required") 
    @Size(max = AppConstants.USERNAME_MAX_LENGTH, message = "Username must be at most " + AppConstants.USERNAME_MAX_LENGTH + " characters") 
    private String username; 
 
    /** 
     * Email address of the submitter. 
     * Python: email = StringField('Email', validators=[DataRequired(), Email()]) 
     */ 
    @NotBlank(message = "Email is required") 
    @Email(message = "Invalid email format") 
    @Size(max = AppConstants.EMAIL_MAX_LENGTH, message = "Email must be at most " + AppConstants.EMAIL_MAX_LENGTH + " characters") 
    private String email; 
 
    /** 
     * Phone number of the submitter. 
     * Python: phone = StringField('Phone', validators=[DataRequired()]) 
     */ 
    @NotBlank(message = "Phone is required") 
    @Pattern(regexp = "^[0-9\\-\\+]{9,20}$", message = "Phone must be a valid number (9-20 digits, may include + or -)") 
    @Size(max = AppConstants.PHONE_MAX_LENGTH, message = "Phone must be at most " + AppConstants.PHONE_MAX_LENGTH + " characters") 
    private String phone; 
 
    /** 
     * Feedback comments. 
     * Python: comments = TextAreaField('Comments', validators=[DataRequired()]) 
     */ 
    @NotBlank(message = "Comments are required") 
    @Size(max = AppConstants.COMMENTS_MAX_LENGTH, message = "Comments must be at most " + AppConstants.COMMENTS_MAX_LENGTH + " characters") 
    private String comments; 
 
    /** 
     * Star rating (enum in Java, int or string in Python). 
     * Python: star_rating = SelectField('Star Rating', coerce=int, choices=[...]) 
     * Java: Uses StarRating enum for type safety. 
     */ 
    @NotNull(message = "Star rating is required") 
    private StarRating starRating; 
 
    /** 
     * Default constructor. 
     * Required for Spring's data binding. 
     */ 
    public FeedbackForm() { 
        // Default constructor 
    } 
 
    /** 
     * Get the username. 
     * @return username 
     */ 
    public String getUsername() { 
        return username; 
    } 
 
    /** 
     * Set the username. 
     * Trims whitespace for consistency with Python's .strip(). 
     * @param username the username 
     */ 
    public void setUsername(String username) { 
        this.username = username != null ? username.trim() : null; 
    } 
 
    /** 
     * Get the email. 
     * @return email 
     */ 
    public String getEmail() { 
        return email; 
    } 
 
    /** 
     * Set the email. 
     * Trims whitespace for consistency with Python's .strip(). 
     * @param email the email 
     */ 
    public void setEmail(String email) { 
        this.email = email != null ? email.trim() : null; 
    } 
 
    /** 
     * Get the phone number. 
     * @return phone 
     */ 
    public String getPhone() { 
        return phone; 
    } 
 
    /** 
     * Set the phone number. 
     * Trims whitespace for consistency with Python's .strip(). 
     * @param phone the phone number 
     */ 
    public void setPhone(String phone) { 
        this.phone = phone != null ? phone.trim() : null; 
    } 
 
    /** 
     * Get the comments. 
     * @return comments 
     */ 
    public String getComments() { 
        return comments; 
    } 
 
    /** 
     * Set the comments. 
     * Trims whitespace for consistency with Python's .strip(). 
     * @param comments the comments 
     */ 
    public void setComments(String comments) { 
        this.comments = comments != null ? comments.trim() : null; 
    } 
 
    /** 
     * Get the star rating. 
     * @return starRating 
     */ 
    public StarRating getStarRating() { 
        return starRating; 
    } 
 
    /** 
     * Set the star rating. 
     * @param starRating the star rating (enum) 
     */ 
    public void setStarRating(StarRating starRating) { 
        this.starRating = starRating; 
    } 
}