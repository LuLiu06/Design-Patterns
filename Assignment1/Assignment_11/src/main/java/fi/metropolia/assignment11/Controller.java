package fi.metropolia.assignment11;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class that handles communication between Model and View.
 * This is the Caretaker in the Memento pattern.
 * 
 * Features:
 * - Undo: Ctrl+Z restores the previous state
 * - Redo: Ctrl+Y restores the last undone state
 * - History: Shows all saved states
 */
public class Controller {
    
    private Model model;
    private Gui gui;
    private List<IMemento> undoHistory;
    private List<IMemento> redoHistory;
    private HistoryWindow historyWindow;
    
    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.undoHistory = new ArrayList<>();
        this.redoHistory = new ArrayList<>();
    }
    
    public void setOption(int optionNumber, int choice) {
        String description = "Box " + optionNumber + " color changed";
        saveToUndoHistory(description);
        clearRedoHistory();
        model.setOption(optionNumber, choice);
        updateHistoryWindow();
    }
    
    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }
    
    public void setIsSelected(boolean isSelected) {
        String description = "Checkbox " + (isSelected ? "checked" : "unchecked");
        saveToUndoHistory(description);
        clearRedoHistory();
        model.setIsSelected(isSelected);
        updateHistoryWindow();
    }
    
    public boolean getIsSelected() {
        return model.getIsSelected();
    }
    
    /**
     * Undo the last action (Ctrl+Z).
     * Saves current state to redo history before restoring.
     */
    public void undo() {
        if (!undoHistory.isEmpty()) {
            System.out.println("Undo: Memento found in history");
            
            // Save current state to redo history
            IMemento currentState = model.createMemento("Before undo");
            redoHistory.add(currentState);
            
            // Restore previous state from undo history
            IMemento previousState = undoHistory.remove(undoHistory.size() - 1);
            model.restoreState(previousState);
            gui.updateGui();
            updateHistoryWindow();
        } else {
            System.out.println("Undo: No more states to undo");
        }
    }
    
    /**
     * Redo the last undone action (Ctrl+Y).
     * Saves current state to undo history before restoring.
     */
    public void redo() {
        if (!redoHistory.isEmpty()) {
            System.out.println("Redo: Memento found in redo history");
            
            // Save current state to undo history
            IMemento currentState = model.createMemento("Before redo");
            undoHistory.add(currentState);
            
            // Restore state from redo history
            IMemento redoState = redoHistory.remove(redoHistory.size() - 1);
            model.restoreState(redoState);
            gui.updateGui();
            updateHistoryWindow();
        } else {
            System.out.println("Redo: No more states to redo");
        }
    }
    
    /**
     * Restores the model to a specific state from history.
     * @param memento the memento to restore
     */
    public void restoreToState(IMemento memento) {
        // Save current state before restoring
        saveToUndoHistory("Before history restore");
        clearRedoHistory();
        
        model.restoreState(memento);
        gui.updateGui();
        updateHistoryWindow();
    }
    
    /**
     * Gets the undo history list for display.
     * @return list of mementos in undo history
     */
    public List<IMemento> getUndoHistory() {
        return new ArrayList<>(undoHistory);
    }
    
    /**
     * Checks if undo is available.
     * @return true if there are states to undo
     */
    public boolean canUndo() {
        return !undoHistory.isEmpty();
    }
    
    /**
     * Checks if redo is available.
     * @return true if there are states to redo
     */
    public boolean canRedo() {
        return !redoHistory.isEmpty();
    }
    
    private void saveToUndoHistory(String description) {
        IMemento currentState = model.createMemento(description);
        undoHistory.add(currentState);
    }
    
    private void clearRedoHistory() {
        if (!redoHistory.isEmpty()) {
            System.out.println("Clearing redo history (new change made)");
            redoHistory.clear();
        }
    }
    
    private void updateHistoryWindow() {
        if (historyWindow != null) {
            historyWindow.updateHistory();
        }
    }
    
    /**
     * Sets the history window reference for updates.
     * @param historyWindow the history window
     */
    public void setHistoryWindow(HistoryWindow historyWindow) {
        this.historyWindow = historyWindow;
    }
}
