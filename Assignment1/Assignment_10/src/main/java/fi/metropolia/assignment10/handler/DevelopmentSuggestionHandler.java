package fi.metropolia.assignment10.handler;

import fi.metropolia.assignment10.message.Message;
import fi.metropolia.assignment10.message.MessageType;

import java.util.Random;

/**
 * Handler for development suggestion feedback.
 * Logs suggestions and assigns priority levels.
 */
public class DevelopmentSuggestionHandler extends FeedbackHandler {
    
    private static final String[] PRIORITIES = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
    private static final String[] CATEGORIES = {
        "UI/UX Improvement",
        "New Feature",
        "Performance Enhancement",
        "Security Enhancement",
        "Integration Request"
    };
    
    private final Random random = new Random();
    
    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.DEVELOPMENT_SUGGESTION;
    }
    
    @Override
    protected String process(Message message) {
        StringBuilder result = new StringBuilder();
        result.append("═".repeat(50)).append("\n");
        result.append("DEVELOPMENT SUGGESTION HANDLER\n");
        result.append("─".repeat(50)).append("\n");
        result.append("From: ").append(message.getSenderEmail()).append("\n");
        result.append("Suggestion: ").append(message.getContent()).append("\n");
        result.append("─".repeat(50)).append("\n");
        
        // Categorize and prioritize the suggestion
        String category = categorize(message.getContent());
        String priority = assignPriority(message.getContent());
        String ticketId = "DEV-" + (System.currentTimeMillis() % 100000);
        
        result.append("Processing Steps:\n");
        result.append("  1. Suggestion received and logged\n");
        result.append("  2. Analyzing suggestion content...\n");
        result.append("  3. Categorizing suggestion...\n");
        result.append("  4. Assigning priority level...\n");
        result.append("  5. Creating development ticket...\n");
        
        result.append("\n✓ SUGGESTION LOGGED\n");
        result.append("  Ticket ID: ").append(ticketId).append("\n");
        result.append("  Category: ").append(category).append("\n");
        result.append("  Priority: ").append(priority).append("\n");
        result.append("  Status: Added to development backlog\n");
        result.append("  Thank you email sent to: ").append(message.getSenderEmail()).append("\n");
        
        result.append("═".repeat(50));
        return result.toString();
    }
    
    private String categorize(String content) {
        String lowerContent = content.toLowerCase();
        
        if (lowerContent.contains("ui") || lowerContent.contains("design") || lowerContent.contains("interface")) {
            return CATEGORIES[0];
        } else if (lowerContent.contains("security") || lowerContent.contains("password")) {
            return CATEGORIES[3];
        } else if (lowerContent.contains("fast") || lowerContent.contains("performance") || lowerContent.contains("slow")) {
            return CATEGORIES[2];
        } else if (lowerContent.contains("integrate") || lowerContent.contains("api")) {
            return CATEGORIES[4];
        } else {
            return CATEGORIES[1]; // New Feature (default)
        }
    }
    
    private String assignPriority(String content) {
        String lowerContent = content.toLowerCase();
        
        if (lowerContent.contains("urgent") || lowerContent.contains("critical") || lowerContent.contains("security")) {
            return PRIORITIES[3];
        } else if (lowerContent.contains("important") || lowerContent.contains("need")) {
            return PRIORITIES[2];
        } else if (lowerContent.contains("nice") || lowerContent.contains("would be good")) {
            return PRIORITIES[0];
        } else {
            return PRIORITIES[1]; // Medium (default)
        }
    }
    
    @Override
    public String getHandlerName() {
        return "Development Suggestion Handler";
    }
}
