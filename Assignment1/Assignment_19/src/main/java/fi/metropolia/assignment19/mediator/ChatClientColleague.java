package fi.metropolia.assignment19.mediator;

/**
 * Colleague interface used by the mediator to deliver UI updates
 * without depending on JavaFX-specific controller types.
 */
public interface ChatClientColleague {
    
    String getUsername();
    
    /**
     * Appends a line to the client's message log (must run on JavaFX thread).
     * @param formattedLine line to display
     */
    void appendLogLine(String formattedLine);
}
