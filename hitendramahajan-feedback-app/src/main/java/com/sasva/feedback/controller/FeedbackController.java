package com.sasva.feedback.controller; 
 
import com.sasva.feedback.dao.FeedbackDao; 
import com.sasva.feedback.dao.FeedbackDaoImpl; 
import com.sasva.feedback.dto.FeedbackForm; 
import com.sasva.feedback.model.Feedback; 
import com.sasva.feedback.model.StarRating; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.*; 
 
import javax.validation.Valid; 
import java.util.List; 
 
/** 
 * Handles web requests related to feedback. 
 * 
 * <p> 
 * This controller replaces the route definitions in Python's <code>app.py</code>: 
 * <ul> 
 *     <li>@GetMapping("/") - corresponds to Flask's <code>@app.route('/')<code></li> 
 *     <li>@GetMapping("/feedback") / @PostMapping("/feedback") - corresponds to Flask's feedback form route</li> 
 *     <li>@GetMapping("/feedback/list") - corresponds to Flask's feedback listing route</li> 
 * </ul> 
 * </p> 
 * 
 * <pre> 
 * # Python (Flask) 
 * @app.route('/feedback', methods=['GET', 'POST']) 
 * def feedback(): 
 *     ... 
 * 
 * // Java (Spring Boot) 
 * @GetMapping("/feedback") 
 * public String showFeedbackForm(...) { ... } 
 * 
 * @PostMapping("/feedback") 
 * public String submitFeedback(...) { ... } 
 * </pre> 
 */ 
@Controller 
public class FeedbackController { 
 
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class); 
 
    // In Python Flask, the DAO or DB connection would be created per request or globally. 
    // In Java Spring, we typically use dependency injection, but for this migration, we instantiate directly. 
    private final FeedbackDao feedbackDao = new FeedbackDaoImpl(); 
 
    /** 
     * Displays the homepage. 
     * Python: @app.route('/') 
     */ 
    @GetMapping("/") 
    public String index() { 
        logger.info("Rendering homepage"); 
        return "index"; 
    } 
 
    /** 
     * Displays the feedback form. 
     * Python: @app.route('/feedback', methods=['GET']) 
     */ 
    @GetMapping("/feedback") 
    public String showFeedbackForm(Model model) { 
        // In Flask: render_template('feedback_form.html', form=form) 
        model.addAttribute("feedbackForm", new FeedbackForm()); 
        model.addAttribute("starRatings", StarRating.values()); 
        return "feedback_form"; 
    } 
 
    /** 
     * Processes feedback form submission. 
     * Python: @app.route('/feedback', methods=['POST']) with WTForms validation 
     * 
     * @param feedbackForm the form data, validated using Bean Validation (JSR-380) 
     * @param bindingResult result of validation 
     * @param model the UI model 
     * @return the view name 
     */ 
    @PostMapping("/feedback") 
    public String submitFeedback( 
            @Valid @ModelAttribute("feedbackForm") FeedbackForm feedbackForm, 
            BindingResult bindingResult, 
            Model model) { 
        // WTForms: if form.validate_on_submit(): 
        if (bindingResult.hasErrors()) { 
            model.addAttribute("starRatings", StarRating.values()); 
            logger.warn("Validation errors in feedback form: {}", bindingResult.getAllErrors()); 
            return "feedback_form"; 
        } 
        try { 
            feedbackDao.saveFeedback(feedbackForm); 
            logger.info("Feedback submitted by {}", feedbackForm.getUsername()); 
            return "redirect:/feedback/list"; 
        } catch (Exception e) { 
            logger.error("Error saving feedback", e); 
            model.addAttribute("errorMessage", "Failed to submit feedback. Please try again."); 
            return "error"; 
        } 
    } 
 
    /** 
     * Lists all feedback entries. 
     * Python: @app.route('/feedback/list') 
     */ 
    @GetMapping("/feedback/list") 
    public String listFeedback(Model model) { 
        try { 
            List<Feedback> feedbackList = feedbackDao.getAllFeedback(); 
            model.addAttribute("feedbackList", feedbackList); 
            return "feedback_list"; 
        } catch (Exception e) { 
            logger.error("Error retrieving feedback list", e); 
            model.addAttribute("errorMessage", "Failed to retrieve feedback list."); 
            return "error"; 
        } 
    } 
}