package fi.metropolia.assignment10.message;

/**
 * Message class representing a customer feedback message.
 * Contains the type of feedback, content, and sender information.
 */
public class Message {
    
    private final MessageType type;
    private final String content;
    private final String senderEmail;
    
    public Message(MessageType type, String content, String senderEmail) {
        this.type = type;
        this.content = content;
        this.senderEmail = senderEmail;
    }
    
    public MessageType getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getSenderEmail() {
        return senderEmail;
    }
    
    @Override
    public String toString() {
        return String.format("Message[type=%s, from=%s, content='%s']", 
                type.getDisplayName(), senderEmail, 
                content.length() > 50 ? content.substring(0, 47) + "..." : content);
    }
}
