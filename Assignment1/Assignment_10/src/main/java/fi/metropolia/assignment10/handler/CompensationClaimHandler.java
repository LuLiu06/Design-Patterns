package fi.metropolia.assignment10.handler;

import fi.metropolia.assignment10.message.Message;
import fi.metropolia.assignment10.message.MessageType;

import java.util.Random;

/**
 * Handler for compensation claim feedback.
 * Reviews claims and approves or rejects them based on evaluation.
 */
public class CompensationClaimHandler extends FeedbackHandler {
    
    private final Random random = new Random();
    
    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.COMPENSATION_CLAIM;
    }
    
    @Override
    protected String process(Message message) {
        StringBuilder result = new StringBuilder();
        result.append("═".repeat(50)).append("\n");
        result.append("COMPENSATION CLAIM HANDLER\n");
        result.append("─".repeat(50)).append("\n");
        result.append("From: ").append(message.getSenderEmail()).append("\n");
        result.append("Claim Details: ").append(message.getContent()).append("\n");
        result.append("─".repeat(50)).append("\n");
        
        // Simulate claim review process
        result.append("Processing Steps:\n");
        result.append("  1. Claim received and logged\n");
        result.append("  2. Reviewing claim validity...\n");
        result.append("  3. Checking customer history...\n");
        result.append("  4. Calculating compensation amount...\n");
        
        // Simulate approval/rejection (70% approval rate)
        boolean approved = random.nextDouble() < 0.7;
        
        if (approved) {
            int compensationAmount = random.nextInt(100) + 50;
            result.append("\n✓ CLAIM APPROVED\n");
            result.append("  Compensation: $").append(compensationAmount).append("\n");
            result.append("  A confirmation email will be sent to: ").append(message.getSenderEmail()).append("\n");
        } else {
            result.append("\n✗ CLAIM REJECTED\n");
            result.append("  Reason: Insufficient evidence or policy violation\n");
            result.append("  A detailed explanation will be sent to: ").append(message.getSenderEmail()).append("\n");
        }
        
        result.append("═".repeat(50));
        return result.toString();
    }
    
    @Override
    public String getHandlerName() {
        return "Compensation Claim Handler";
    }
}
