package com.sasva.feedback.model; 
 
import java.time.LocalDateTime; 
 
/** 
 * Represents a feedback entry. 
 * 
 * <p> 
 * <b>Python mapping:</b> 
 * <ul> 
 *   <li>In the original Python Flask app, feedback was typically represented as a dictionary or as a simple ORM model (if using SQLAlchemy), e.g.:</li> 
 * </ul> 
 * <pre> 
 * # Python (Flask) 
 * feedback = { 
 *     'id': ..., 
 *     'username': ..., 
 *     'email': ..., 
 *     'phone': ..., 
 *     'comments': ..., 
 *     'star_rating': ..., 
 *     'submitted_at': ... 
 * } 
 * </pre> 
 * <ul> 
 *   <li>In Java, we use a POJO (Plain Old Java Object) for type safety, encapsulation, and integration with frameworks like Spring and JDBC.</li> 
 *   <li>Field types are explicit, and we use Java's LocalDateTime for date fields instead of Python's datetime.</li> 
 *   <li>In the original Python code, the star rating might be a string or integer; in Java, we often use an enum (see {@link com.sasva.feedback.model.StarRating}) for type safety.</li> 
 * </ul> 
 * </p> 
 * 
 * <p> 
 * <b>Major translation points:</b> 
 * <ul> 
 *   <li>Python's dynamic typing ➔ Java's static typing (all fields have explicit types).</li> 
 *   <li>Python dict or ORM model ➔ Java class with private fields and public getters/setters.</li> 
 *   <li>Python's datetime.datetime ➔ Java's java.time.LocalDateTime.</li> 
 *   <li>Python's star_rating (int or str) ➔ Java's String or Enum (see StarRating.java).</li> 
 * </ul> 
 * </p> 
 * 
 * <p> 
 * <b>Example translation:</b> 
 * <pre> 
 * # Python 
 * feedback = { 
 *     'username': form.username.data, 
 *     'email': form.email.data, 
 *     'phone': form.phone.data, 
 *     'comments': form.comments.data, 
 *     'star_rating': form.star_rating.data, 
 *     'submitted_at': datetime.utcnow() 
 * } 
 * 
 * // Java 
 * Feedback feedback = new Feedback(); 
 * feedback.setUsername(form.getUsername()); 
 * feedback.setEmail(form.getEmail()); 
 * feedback.setPhone(form.getPhone()); 
 * feedback.setComments(form.getComments()); 
 * feedback.setStarRating(form.getStarRating()); 
 * feedback.setSubmittedAt(LocalDateTime.now()); 
 * </pre> 
 * </p> 
 */ 
public class Feedback { 
    private int id; 
    private String username; 
    private String email; 
    private String phone; 
    private String comments; 
    private String starRating; 
    private LocalDateTime submittedAt; 
 
    /** 
     * Default constructor. 
     * <p> 
     * In Python, you could create a feedback dict on the fly. 
     * In Java, we provide a no-arg constructor for frameworks and manual instantiation. 
     * </p> 
     */ 
    public Feedback() {} 
 
    /** 
     * All-args constructor. 
     * <p> 
     * Useful for quickly creating a Feedback object with all fields set. 
     * </p> 
     */ 
    public Feedback(int id, String username, String email, String phone, String comments, String starRating, LocalDateTime submittedAt) { 
        this.id = id; 
        this.username = username; 
        this.email = email; 
        this.phone = phone; 
        this.comments = comments; 
        this.starRating = starRating; 
        this.submittedAt = submittedAt; 
    } 
 
    /** 
     * Gets the feedback ID. 
     * <p> 
     * Python: feedback['id'] 
     * </p> 
     */ 
    public int getId() { 
        return id; 
    } 
 
    /** 
     * Sets the feedback ID. 
     */ 
    public void setId(int id) { 
        this.id = id; 
    } 
 
    /** 
     * Gets the username of the feedback submitter. 
     * <p> 
     * Python: feedback['username'] 
     * </p> 
     */ 
    public String getUsername() { 
        return username; 
    } 
 
    /** 
     * Sets the username of the feedback submitter. 
     */ 
    public void setUsername(String username) { 
        this.username = username; 
    } 
 
    /** 
     * Gets the email of the feedback submitter. 
     * <p> 
     * Python: feedback['email'] 
     * </p> 
     */ 
    public String getEmail() { 
        return email; 
    } 
 
    /** 
     * Sets the email of the feedback submitter. 
     */ 
    public void setEmail(String email) { 
        this.email = email; 
    } 
 
    /** 
     * Gets the phone number of the feedback submitter. 
     * <p> 
     * Python: feedback['phone'] 
     * </p> 
     */ 
    public String getPhone() { 
        return phone; 
    } 
 
    /** 
     * Sets the phone number of the feedback submitter. 
     */ 
    public void setPhone(String phone) { 
        this.phone = phone; 
    } 
 
    /** 
     * Gets the comments provided in the feedback. 
     * <p> 
     * Python: feedback['comments'] 
     * </p> 
     */ 
    public String getComments() { 
        return comments; 
    } 
 
    /** 
     * Sets the comments provided in the feedback. 
     */ 
    public void setComments(String comments) { 
        this.comments = comments; 
    } 
 
    /** 
     * Gets the star rating as a string. 
     * <p> 
     * Python: feedback['star_rating'] 
     * In Java, this could be a String or an Enum (see StarRating.java). 
     * </p> 
     */ 
    public String getStarRating() { 
        return starRating; 
    } 
 
    /** 
     * Sets the star rating. 
     */ 
    public void setStarRating(String starRating) { 
        this.starRating = starRating; 
    } 
 
    /** 
     * Gets the submission timestamp. 
     * <p> 
     * Python: feedback['submitted_at'] 
     * Java: LocalDateTime 
     * </p> 
     */ 
    public LocalDateTime getSubmittedAt() { 
        return submittedAt; 
    } 
 
    /** 
     * Sets the submission timestamp. 
     */ 
    public void setSubmittedAt(LocalDateTime submittedAt) { 
        this.submittedAt = submittedAt; 
    } 
}