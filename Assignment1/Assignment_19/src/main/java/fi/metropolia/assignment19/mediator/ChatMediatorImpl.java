package fi.metropolia.assignment19.mediator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Central mediator: routes messages between registered colleagues only.
 */
public class ChatMediatorImpl implements ChatMediator {
    
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private final Map<String, ChatClientColleague> clients = new LinkedHashMap<>();
    
    @Override
    public void registerClient(String username, ChatClientColleague colleague) {
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(colleague, "colleague");
        clients.put(username, colleague);
        broadcastSystem(username + " joined the chat.");
    }
    
    @Override
    public void unregisterClient(String username) {
        if (clients.remove(username) != null) {
            broadcastSystem(username + " left the chat.");
        }
    }
    
    @Override
    public void sendMessage(String fromUsername, String toUsername, String text) {
        Objects.requireNonNull(fromUsername, "fromUsername");
        Objects.requireNonNull(toUsername, "toUsername");
        String body = text == null ? "" : text.trim();
        
        if (body.isEmpty()) {
            deliverTo(fromUsername, formatSystem("Message is empty; nothing sent."));
            return;
        }
        
        ChatClientColleague recipient = clients.get(toUsername);
        if (recipient == null) {
            deliverTo(fromUsername, formatSystem("Unknown recipient: " + toUsername));
            return;
        }
        
        if (fromUsername.equals(toUsername)) {
            deliverTo(fromUsername, formatSystem("You cannot send a message to yourself."));
            return;
        }
        
        String outbound = formatUserMessage(fromUsername, toUsername, body, false);
        String inbound = formatUserMessage(fromUsername, toUsername, body, true);
        
        deliverTo(fromUsername, outbound);
        deliverTo(toUsername, inbound);
    }
    
    private void broadcastSystem(String text) {
        String line = formatSystem(text);
        for (ChatClientColleague c : clients.values()) {
            deliverTo(c.getUsername(), line);
        }
    }
    
    private void deliverTo(String username, String line) {
        ChatClientColleague c = clients.get(username);
        if (c == null) {
            return;
        }
        c.appendLogLine(line);
    }
    
    private String formatUserMessage(String from, String to, String body, boolean forRecipient) {
        String time = LocalTime.now().format(TIME);
        if (forRecipient) {
            return String.format("[%s] From %s → You: %s", time, from, body);
        }
        return String.format("[%s] You → %s: %s", time, to, body);
    }
    
    private String formatSystem(String text) {
        return String.format("[%s] [System] %s", LocalTime.now().format(TIME), text);
    }
}
