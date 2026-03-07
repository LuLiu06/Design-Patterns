package fi.metropolia.assignment10.handler;

import fi.metropolia.assignment10.message.Message;

/**
 * Abstract handler class for the Chain of Responsibility pattern.
 * Each concrete handler processes a specific type of feedback and
 * passes unhandled messages to the next handler in the chain.
 */
public abstract class FeedbackHandler {
    
    protected FeedbackHandler nextHandler;
    
    /**
     * Sets the next handler in the chain.
     * @param nextHandler the next handler
     * @return the next handler (for chaining)
     */
    public FeedbackHandler setNext(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }
    
    /**
     * Handles the feedback message.
     * If this handler can process the message, it does so.
     * Otherwise, it passes the message to the next handler in the chain.
     * @param message the feedback message to handle
     * @return the result of handling the message
     */
    public String handle(Message message) {
        if (canHandle(message)) {
            return process(message);
        } else if (nextHandler != null) {
            return nextHandler.handle(message);
        } else {
            return "No handler available for message type: " + message.getType().getDisplayName();
        }
    }
    
    /**
     * Checks if this handler can process the given message.
     * @param message the message to check
     * @return true if this handler can process the message
     */
    protected abstract boolean canHandle(Message message);
    
    /**
     * Processes the message and returns the result.
     * @param message the message to process
     * @return the result of processing
     */
    protected abstract String process(Message message);
    
    /**
     * Gets the name of this handler.
     * @return handler name
     */
    public abstract String getHandlerName();
}
