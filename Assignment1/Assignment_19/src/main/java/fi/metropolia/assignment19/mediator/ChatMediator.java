package fi.metropolia.assignment19.mediator;

/**
 * Mediator for chat clients. Clients never call each other directly;
 * all message routing goes through the mediator.
 */
public interface ChatMediator {
    
    /**
     * Registers a client with the mediator.
     * @param username unique username
     * @param colleague client endpoint the mediator can deliver to
     */
    void registerClient(String username, ChatClientColleague colleague);
    
    /**
     * Unregisters a client (e.g. when window closes).
     * @param username username to remove
     */
    void unregisterClient(String username);
    
    /**
     * Sends a message from one user to another via the mediator.
     * @param fromUsername sender
     * @param toUsername recipient
     * @param text message body
     */
    void sendMessage(String fromUsername, String toUsername, String text);
}
