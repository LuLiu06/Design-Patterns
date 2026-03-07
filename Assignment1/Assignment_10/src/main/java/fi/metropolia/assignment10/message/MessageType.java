package fi.metropolia.assignment10.message;

/**
 * Enumerated type representing different forms of customer feedback.
 */
public enum MessageType {
    COMPENSATION_CLAIM("Compensation Claim"),
    CONTACT_REQUEST("Contact Request"),
    DEVELOPMENT_SUGGESTION("Development Suggestion"),
    GENERAL_FEEDBACK("General Feedback");
    
    private final String displayName;
    
    MessageType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
