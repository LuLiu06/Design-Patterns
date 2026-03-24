package fi.metropolia.assignment11;

import java.time.LocalDateTime;

/**
 * Memento metadata interface.
 * Exposes metadata about the saved state to the caretaker.
 */
public interface IMemento {
    
    /**
     * Gets the timestamp when this memento was created.
     * @return the creation timestamp
     */
    LocalDateTime getTimestamp();
    
    /**
     * Gets a description of the state change.
     * @return description of the change
     */
    String getDescription();
    
    /**
     * Gets a formatted string representation for display in the history list.
     * @return formatted display string
     */
    String getDisplayText();
}
