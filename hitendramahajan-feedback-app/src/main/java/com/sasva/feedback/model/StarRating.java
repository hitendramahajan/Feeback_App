package com.sasva.feedback.model; 
 
/** 
 * Enum representing star ratings for feedback. 
 * Provides type safety and mapping between integer values and display labels. 
 */ 
public enum StarRating { 
    ONE(1, "★"), 
    TWO(2, "★★"), 
    THREE(3, "★★★"), 
    FOUR(4, "★★★★"), 
    FIVE(5, "★★★★★"); 
 
    private final int value; 
    private final String label; 
 
    StarRating(int value, String label) { 
        this.value = value; 
        this.label = label; 
    } 
 
    /** 
     * Gets the integer value of the star rating. 
     * @return the integer value (1-5) 
     */ 
    public int getValue() { 
        return value; 
    } 
 
    /** 
     * Gets the display label for the star rating. 
     * @return the label (e.g., "★★★") 
     */ 
    public String getLabel() { 
        return label; 
    } 
 
    /** 
     * Returns the StarRating enum corresponding to the given integer value. 
     * @param value the integer value (1-5) 
     * @return the corresponding StarRating 
     * @throws IllegalArgumentException if the value is not between 1 and 5 
     */ 
    public static StarRating fromValue(int value) { 
        for (StarRating rating : StarRating.values()) { 
            if (rating.value == value) { 
                return rating; 
            } 
        } 
        throw new IllegalArgumentException("Invalid star rating: " + value); 
    } 
} 