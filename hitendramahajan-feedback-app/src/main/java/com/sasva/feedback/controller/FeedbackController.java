package com.sasva.feedback.controller; 
 
import com.sasva.feedback.dao.FeedbackDao; 
import com.sasva.feedback.dto.FeedbackForm; 
import com.sasva.feedback.model.Feedback; 
import com.sasva.feedback.model.StarRating; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping; 
 
import javax.validation.Valid; 
import java.util.List; 
 
@Controller 
public class FeedbackController { 
 
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class); 
 
    @Autowired 
    private FeedbackDao feedbackDao; 
 
    // Homepage route ("/") 
    @GetMapping("/") 
    public String showHome() { 
        logger.info("Accessed home page."); 
        return "index"; 
    } 
 
    // Feedback form route ("/feedback") 
    @GetMapping("/feedback") 
    public String showFeedbackForm(Model model) { 
        logger.info("Accessed feedback form page."); 
        model.addAttribute("feedbackForm", new FeedbackForm()); 
        // Add all possible star ratings for the form select field 
        model.addAttribute("starRatings", StarRating.values()); 
        return "feedback_form"; 
    } 
 
    // Handle feedback form submission 
    @PostMapping("/feedback") 
    public String submitFeedback( 
            @Valid @ModelAttribute("feedbackForm") FeedbackForm feedbackForm, 
            BindingResult bindingResult, 
            Model model) { 
        if (bindingResult.hasErrors()) { 
            logger.warn("Feedback form validation failed: {}", bindingResult.getAllErrors()); 
            model.addAttribute("errorMessage", "Please correct the errors in the form."); 
            model.addAttribute("starRatings", StarRating.values()); 
            return "feedback_form"; 
        } 
        Feedback feedback = null; 
        try { 
            feedback = new Feedback(feedbackForm); 
        } catch (Exception ex) { 
            logger.error("Error mapping FeedbackForm to Feedback: {}", ex.getMessage(), ex); 
            model.addAttribute("errorMessage", "An error occurred while processing your feedback. Please try again."); 
            model.addAttribute("starRatings", StarRating.values()); 
            return "feedback_form"; 
        } 
        boolean saved = false; 
        try { 
            saved = feedbackDao.saveFeedback(feedback); 
        } catch (Exception ex) { 
            logger.error("Exception while saving feedback: {}", ex.getMessage(), ex); 
        } 
        if (!saved) { 
            logger.error("Failed to save feedback for user: {}", feedback != null ? feedback.getUsername() : "unknown"); 
            model.addAttribute("errorMessage", "An error occurred while submitting your feedback. Please try again."); 
            model.addAttribute("starRatings", StarRating.values()); 
            return "feedback_form"; 
        } 
        logger.info("Feedback submitted successfully by user: {}", feedback.getUsername()); 
        model.addAttribute("successMessage", "Thank you for your feedback!"); 
        model.addAttribute("feedbackForm", new FeedbackForm()); 
        model.addAttribute("starRatings", StarRating.values()); 
        return "feedback_form"; 
    } 
 
    // Feedback list (admin) route ("/feedback/list") 
    @GetMapping("/feedback/list") 
    public String listFeedback(Model model) { 
        try { 
            List<Feedback> feedbackList = feedbackDao.getAllFeedback(); 
            model.addAttribute("feedbackList", feedbackList); 
            logger.info("Feedback list page accessed, {} entries loaded.", feedbackList.size()); 
        } catch (Exception ex) { 
            logger.error("Error loading feedback list: {}", ex.getMessage(), ex); 
            model.addAttribute("errorMessage", "Could not load feedback list. Please try again later."); 
        } 
        return "feedback_list"; 
    } 
}