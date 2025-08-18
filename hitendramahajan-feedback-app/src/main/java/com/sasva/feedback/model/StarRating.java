package com.sasva.feedback.model; 
 
/** 
 * Enum for star ratings. 
 * 
 * <p> 
 * In the original Python Flask/WTForms/MySQL application, star ratings were typically represented 
 * as a list of tuples, constants, or integer values (1-5) for form choices and database storage. 
 * For example, in Python/WTForms: 
 * 
 * <pre> 
 * STAR_RATINGS = [ 
 *     (1, '★'), 
 *     (2, '★★'), 
 *     (3, '★★★'), 
 *     (4, '★★★★'), 
 *     (5, '★★★★★') 
 * ] 
 * star_rating = SelectField('Star Rating', choices=STAR_RATINGS, coerce=int) 
 * </pre> 
 * 
 * In Java, we use an enum for type safety, clarity, and to encapsulate both the integer value and 
 * the display label. This approach prevents invalid values and makes it easier to work with star 
 * ratings throughout the application. 
 * </p> 
 * 
 * <p> 
 * <b>Major translation points:</b> 
 * <ul> 
 *   <li>Python's dynamic tuple/list for choices → Java's enum with value and label fields.</li> 
 *   <li>WTForms coerce=int → Java uses int value and static lookup methods.</li> 
 *   <li>String/integer conversion logic is explicit in Java (see fromInt/fromString).</li> 
 * </ul> 
 * </p> 
 * 
 * <p> 
 * <b>Example usage:</b> 
 * <pre> 
 * // Python: form.star_rating.data  # int 
 * // Java:  StarRating.fromString(form.getStarRating())  // returns StarRating enum 
 * </pre> 
 * </p> 
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
     * <p> 
     * Python: <code>form.star_rating.data</code> (int) 
     * </p> 
     * @return the integer value (1-5) 
     */ 
    public int getValue() { 
        return value; 
    } 
 
    /** 
     * Gets the display label for the star rating. 
     * <p> 
     * Python: <code>dict(STAR_RATINGS)[form.star_rating.data]</code> 
     * </p> 
     * @return the label (e.g., "★★★") 
     */ 
    public String getLabel() { 
        return label; 
    } 
 
    /** 
     * Returns the StarRating enum corresponding to the given integer value. 
     * <p> 
     * Python: <code>next((s for s in STAR_RATINGS if s[0] == value), None)</code> 
     * </p> 
     * @param value the integer value (1-5) 
     * @return the corresponding StarRating 
     * @throws IllegalArgumentException if the value is not between 1 and 5 
     */ 
    public static StarRating fromInt(int value) { 
        switch (value) { 
            case 1: return ONE; 
            case 2: return TWO; 
            case 3: return THREE; 
            case 4: return FOUR; 
            case 5: return FIVE; 
            default: 
                // Logging for error handling 
                System.err.println("Invalid star rating value received in StarRating.fromInt: " + value); 
                throw new IllegalArgumentException("Invalid star rating: " + value); 
        } 
    } 
 
    /** 
     * Returns the StarRating enum corresponding to the given string value. 
     * Accepts both integer strings ("1"-"5") and enum names ("ONE"-"FIVE"). 
     * <p> 
     * Python: WTForms SelectField returns string/int, so this method handles both. 
     * </p> 
     * @param value the string value 
     * @return the corresponding StarRating 
     * @throws IllegalArgumentException if the value is invalid 
     */ 
    public static StarRating fromString(String value) { 
        if (value == null) { 
            System.err.println("Null value received in StarRating.fromString"); 
            throw new IllegalArgumentException("Star rating value cannot be null"); 
        } 
        try { 
            int intValue = Integer.parseInt(value); 
            return fromInt(intValue); 
        } catch (NumberFormatException e) { 
            // Try enum name 
            try { 
                return StarRating.valueOf(value.toUpperCase()); 
            } catch (IllegalArgumentException ex) { 
                System.err.println("Invalid star rating string received in StarRating.fromString: " + value); 
                throw new IllegalArgumentException("Invalid star rating: " + value, ex); 
            } 
        } 
    } 
}