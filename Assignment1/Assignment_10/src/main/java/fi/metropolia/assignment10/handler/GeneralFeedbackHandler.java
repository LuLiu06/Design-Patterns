package fi.metropolia.assignment10.handler;

import fi.metropolia.assignment10.message.Message;
import fi.metropolia.assignment10.message.MessageType;

/**
 * Handler for general feedback.
 * Analyzes sentiment and generates appropriate responses.
 */
public class GeneralFeedbackHandler extends FeedbackHandler {
    
    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.GENERAL_FEEDBACK;
    }
    
    @Override
    protected String process(Message message) {
        StringBuilder result = new StringBuilder();
        result.append("═".repeat(50)).append("\n");
        result.append("GENERAL FEEDBACK HANDLER\n");
        result.append("─".repeat(50)).append("\n");
        result.append("From: ").append(message.getSenderEmail()).append("\n");
        result.append("Feedback: ").append(message.getContent()).append("\n");
        result.append("─".repeat(50)).append("\n");
        
        // Analyze sentiment
        String sentiment = analyzeSentiment(message.getContent());
        String feedbackId = "FB-" + (System.currentTimeMillis() % 100000);
        
        result.append("Processing Steps:\n");
        result.append("  1. Feedback received\n");
        result.append("  2. Analyzing sentiment...\n");
        result.append("  3. Categorizing feedback...\n");
        result.append("  4. Generating response...\n");
        
        result.append("\n✓ FEEDBACK ANALYZED\n");
        result.append("  Feedback ID: ").append(feedbackId).append("\n");
        result.append("  Sentiment: ").append(sentiment).append("\n");
        result.append("  Response: ").append(generateResponse(sentiment)).append("\n");
        result.append("  Response email sent to: ").append(message.getSenderEmail()).append("\n");
        
        result.append("═".repeat(50));
        return result.toString();
    }
    
    private String analyzeSentiment(String content) {
        String lowerContent = content.toLowerCase();
        
        int positiveScore = 0;
        int negativeScore = 0;
        
        // Positive keywords
        String[] positiveWords = {"good", "great", "excellent", "love", "amazing", "wonderful", "thank", "happy", "satisfied", "best"};
        for (String word : positiveWords) {
            if (lowerContent.contains(word)) {
                positiveScore++;
            }
        }
        
        // Negative keywords
        String[] negativeWords = {"bad", "terrible", "awful", "hate", "disappointed", "worst", "poor", "unhappy", "frustrated", "angry"};
        for (String word : negativeWords) {
            if (lowerContent.contains(word)) {
                negativeScore++;
            }
        }
        
        if (positiveScore > negativeScore) {
            return "POSITIVE";
        } else if (negativeScore > positiveScore) {
            return "NEGATIVE";
        } else {
            return "NEUTRAL";
        }
    }
    
    private String generateResponse(String sentiment) {
        return switch (sentiment) {
            case "POSITIVE" -> "Thank you for your kind words! We're glad you're satisfied.";
            case "NEGATIVE" -> "We're sorry to hear about your experience. We'll work to improve.";
            default -> "Thank you for your feedback. We value your input.";
        };
    }
    
    @Override
    public String getHandlerName() {
        return "General Feedback Handler";
    }
}
