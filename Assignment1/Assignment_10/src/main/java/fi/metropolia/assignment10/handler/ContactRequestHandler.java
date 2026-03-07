package fi.metropolia.assignment10.handler;

import fi.metropolia.assignment10.message.Message;
import fi.metropolia.assignment10.message.MessageType;

/**
 * Handler for contact request feedback.
 * Forwards requests to the appropriate department.
 */
public class ContactRequestHandler extends FeedbackHandler {
    
    private static final String[] DEPARTMENTS = {
        "Sales Department",
        "Technical Support",
        "Customer Service",
        "Billing Department",
        "Product Management"
    };
    
    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.CONTACT_REQUEST;
    }
    
    @Override
    protected String process(Message message) {
        StringBuilder result = new StringBuilder();
        result.append("═".repeat(50)).append("\n");
        result.append("CONTACT REQUEST HANDLER\n");
        result.append("─".repeat(50)).append("\n");
        result.append("From: ").append(message.getSenderEmail()).append("\n");
        result.append("Request: ").append(message.getContent()).append("\n");
        result.append("─".repeat(50)).append("\n");
        
        // Determine appropriate department based on content keywords
        String department = determineDepartment(message.getContent());
        
        result.append("Processing Steps:\n");
        result.append("  1. Contact request received\n");
        result.append("  2. Analyzing request content...\n");
        result.append("  3. Identifying appropriate department...\n");
        result.append("  4. Forwarding to: ").append(department).append("\n");
        
        result.append("\n✓ REQUEST FORWARDED\n");
        result.append("  Department: ").append(department).append("\n");
        result.append("  Expected response time: 24-48 hours\n");
        result.append("  Reference number: CR-").append(System.currentTimeMillis() % 100000).append("\n");
        result.append("  Confirmation sent to: ").append(message.getSenderEmail()).append("\n");
        
        result.append("═".repeat(50));
        return result.toString();
    }
    
    private String determineDepartment(String content) {
        String lowerContent = content.toLowerCase();
        
        if (lowerContent.contains("price") || lowerContent.contains("buy") || lowerContent.contains("purchase")) {
            return DEPARTMENTS[0]; // Sales
        } else if (lowerContent.contains("bug") || lowerContent.contains("error") || lowerContent.contains("technical")) {
            return DEPARTMENTS[1]; // Technical Support
        } else if (lowerContent.contains("bill") || lowerContent.contains("payment") || lowerContent.contains("invoice")) {
            return DEPARTMENTS[3]; // Billing
        } else if (lowerContent.contains("feature") || lowerContent.contains("product")) {
            return DEPARTMENTS[4]; // Product Management
        } else {
            return DEPARTMENTS[2]; // Customer Service (default)
        }
    }
    
    @Override
    public String getHandlerName() {
        return "Contact Request Handler";
    }
}
