package fi.metropolia.assignment10;

import fi.metropolia.assignment10.handler.*;
import fi.metropolia.assignment10.message.Message;
import fi.metropolia.assignment10.message.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Main program demonstrating the Chain of Responsibility pattern
 * for handling different types of customer feedback.
 */
public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        // Create the handler chain
        FeedbackHandler handlerChain = createHandlerChain();
        
        // Create test messages
        List<Message> messages = createTestMessages();
        
        // Process each message through the chain
        System.out.println("\nProcessing " + messages.size() + " customer feedback messages...\n");
        
        int messageNumber = 1;
        for (Message message : messages) {
            System.out.println("\n" + "▓".repeat(60));
            System.out.printf("MESSAGE #%d%n", messageNumber++);
            System.out.println("▓".repeat(60));
            System.out.println("Input: " + message);
            System.out.println();
            
            // Handle the message through the chain
            String result = handlerChain.handle(message);
            System.out.println(result);
        }
        
        printSummary(messages);
    }
    
    private static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     CUSTOMER FEEDBACK HANDLER SYSTEM                     ║");
        System.out.println("║     ────────────────────────────────                     ║");
        System.out.println("║     Chain of Responsibility Pattern Demo                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println("\nHandler Chain Structure:");
        System.out.println("  ┌─────────────────────┐");
        System.out.println("  │ Compensation Claim  │");
        System.out.println("  │      Handler        │");
        System.out.println("  └─────────┬───────────┘");
        System.out.println("            ▼");
        System.out.println("  ┌─────────────────────┐");
        System.out.println("  │  Contact Request    │");
        System.out.println("  │      Handler        │");
        System.out.println("  └─────────┬───────────┘");
        System.out.println("            ▼");
        System.out.println("  ┌─────────────────────┐");
        System.out.println("  │    Development      │");
        System.out.println("  │ Suggestion Handler  │");
        System.out.println("  └─────────┬───────────┘");
        System.out.println("            ▼");
        System.out.println("  ┌─────────────────────┐");
        System.out.println("  │  General Feedback   │");
        System.out.println("  │      Handler        │");
        System.out.println("  └─────────────────────┘");
    }
    
    /**
     * Creates and chains the feedback handlers.
     * @return the first handler in the chain
     */
    private static FeedbackHandler createHandlerChain() {
        FeedbackHandler compensationHandler = new CompensationClaimHandler();
        FeedbackHandler contactHandler = new ContactRequestHandler();
        FeedbackHandler suggestionHandler = new DevelopmentSuggestionHandler();
        FeedbackHandler generalHandler = new GeneralFeedbackHandler();
        
        // Chain the handlers
        compensationHandler.setNext(contactHandler);
        contactHandler.setNext(suggestionHandler);
        suggestionHandler.setNext(generalHandler);
        
        return compensationHandler;
    }
    
    /**
     * Creates a list of test messages representing various types of customer feedback.
     * @return list of test messages
     */
    private static List<Message> createTestMessages() {
        List<Message> messages = new ArrayList<>();
        
        // Compensation Claims
        messages.add(new Message(
            MessageType.COMPENSATION_CLAIM,
            "My order #12345 arrived damaged. I request a full refund of $150.",
            "john.doe@email.com"
        ));
        
        messages.add(new Message(
            MessageType.COMPENSATION_CLAIM,
            "The product stopped working after 2 days. I need compensation for the defective item.",
            "jane.smith@email.com"
        ));
        
        // Contact Requests
        messages.add(new Message(
            MessageType.CONTACT_REQUEST,
            "I want to know the price for bulk purchases. Please contact me.",
            "buyer@company.com"
        ));
        
        messages.add(new Message(
            MessageType.CONTACT_REQUEST,
            "Having a technical bug with the mobile app. Need help from technical support.",
            "user123@email.com"
        ));
        
        // Development Suggestions
        messages.add(new Message(
            MessageType.DEVELOPMENT_SUGGESTION,
            "It would be nice to have dark mode UI in the mobile app.",
            "designer@email.com"
        ));
        
        messages.add(new Message(
            MessageType.DEVELOPMENT_SUGGESTION,
            "Urgent security concern: need two-factor authentication for login.",
            "security.expert@email.com"
        ));
        
        // General Feedback
        messages.add(new Message(
            MessageType.GENERAL_FEEDBACK,
            "Great service! I love using your platform. The customer support is excellent!",
            "happy.customer@email.com"
        ));
        
        messages.add(new Message(
            MessageType.GENERAL_FEEDBACK,
            "The new update is terrible. I'm very disappointed with the changes.",
            "unhappy.user@email.com"
        ));
        
        messages.add(new Message(
            MessageType.GENERAL_FEEDBACK,
            "Just wanted to share my thoughts about the recent changes to the homepage.",
            "neutral.user@email.com"
        ));
        
        return messages;
    }
    
    private static void printSummary(List<Message> messages) {
        System.out.println("\n\n" + "═".repeat(60));
        System.out.println("PROCESSING SUMMARY");
        System.out.println("═".repeat(60));
        
        // Count messages by type
        int compensation = 0, contact = 0, suggestion = 0, general = 0;
        
        for (Message msg : messages) {
            switch (msg.getType()) {
                case COMPENSATION_CLAIM -> compensation++;
                case CONTACT_REQUEST -> contact++;
                case DEVELOPMENT_SUGGESTION -> suggestion++;
                case GENERAL_FEEDBACK -> general++;
            }
        }
        
        System.out.println("\nMessages Processed by Type:");
        System.out.printf("  • Compensation Claims:      %d%n", compensation);
        System.out.printf("  • Contact Requests:         %d%n", contact);
        System.out.printf("  • Development Suggestions:  %d%n", suggestion);
        System.out.printf("  • General Feedback:         %d%n", general);
        System.out.printf("  ─────────────────────────────%n");
        System.out.printf("  Total:                      %d%n", messages.size());
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("All customer feedback has been processed successfully!");
        System.out.println("═".repeat(60));
    }
}
