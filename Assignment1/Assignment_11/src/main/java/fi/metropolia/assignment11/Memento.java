package fi.metropolia.assignment11;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Memento class that stores the state of the Model.
 * Implements IMemento to provide metadata to the caretaker.
 */
public class Memento implements IMemento {
    
    private final int[] options;
    private final boolean isSelected;
    private final LocalDateTime timestamp;
    private final String description;
    
    private static final DateTimeFormatter FORMATTER = 
            DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String[] COLOR_NAMES = {"Red", "Blue", "Yellow"};
    
    public Memento(int[] options, boolean isSelected, String description) {
        this.options = options.clone();
        this.isSelected = isSelected;
        this.timestamp = LocalDateTime.now();
        this.description = description;
        System.out.println("Memento created: " + description);
    }
    
    public int[] getOptions() {
        return options.clone();
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public String getDisplayText() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(timestamp.format(FORMATTER)).append("] ");
        sb.append(description);
        sb.append(" | Boxes: ");
        sb.append(COLOR_NAMES[options[0]]).append(", ");
        sb.append(COLOR_NAMES[options[1]]).append(", ");
        sb.append(COLOR_NAMES[options[2]]);
        sb.append(" | Checkbox: ").append(isSelected ? "ON" : "OFF");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getDisplayText();
    }
}
