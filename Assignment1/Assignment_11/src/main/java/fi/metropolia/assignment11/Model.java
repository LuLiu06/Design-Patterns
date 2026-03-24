package fi.metropolia.assignment11;

/**
 * Model class that contains the application's state.
 * This is the Originator in the Memento pattern.
 */
public class Model {
    
    private int[] options = new int[3];
    private boolean isSelected;
    
    public void setOption(int optionNumber, int choice) {
        System.out.println("optionNumber: " + optionNumber + " choice: " + choice);
        if (optionNumber >= 1 && optionNumber <= 3) {
            options[optionNumber - 1] = choice;
        }
    }
    
    public int getOption(int optionNumber) {
        if (optionNumber >= 1 && optionNumber <= 3) {
            return options[optionNumber - 1];
        }
        return -1;
    }
    
    public void setIsSelected(boolean isSelected) {
        System.out.println("isSelected: " + isSelected);
        this.isSelected = isSelected;
    }
    
    public boolean getIsSelected() {
        return isSelected;
    }
    
    /**
     * Creates a memento with the current state.
     * @param description description of the change that led to this state
     * @return a new Memento containing the current state
     */
    public IMemento createMemento(String description) {
        return new Memento(options, isSelected, description);
    }
    
    /**
     * Restores the state from a memento.
     * @param memento the memento to restore from
     */
    public void restoreState(IMemento memento) {
        Memento m = (Memento) memento;
        options = m.getOptions();
        System.out.println("options: " + options[0] + " " + options[1] + " " + options[2]);
        isSelected = m.isSelected();
        System.out.println("isSelected: " + isSelected);
        System.out.println("State restored");
    }
}
