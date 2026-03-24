package fi.metropolia.assignment11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * History window that displays the history of model state changes.
 * Users can click on any state in the list to restore the model to that state.
 */
public class HistoryWindow extends Stage {
    
    private Controller controller;
    private ListView<IMemento> listView;
    private ObservableList<IMemento> historyItems;
    private Label statusLabel;
    
    public HistoryWindow(Controller controller) {
        this.controller = controller;
        this.historyItems = FXCollections.observableArrayList();
        
        initializeUI();
        controller.setHistoryWindow(this);
        updateHistory();
    }
    
    private void initializeUI() {
        setTitle("State History");
        
        // Create instructions label
        Label instructionLabel = new Label("Click on a state to restore it:");
        instructionLabel.setStyle("-fx-font-weight: bold;");
        
        // Create ListView for history
        listView = new ListView<>(historyItems);
        listView.setPrefHeight(300);
        listView.setPrefWidth(500);
        
        // Handle selection
        listView.setOnMouseClicked(event -> {
            IMemento selectedMemento = listView.getSelectionModel().getSelectedItem();
            if (selectedMemento != null) {
                System.out.println("Restoring state: " + selectedMemento.getDescription());
                controller.restoreToState(selectedMemento);
                statusLabel.setText("Restored: " + selectedMemento.getDescription());
            }
        });
        
        // Status label
        statusLabel = new Label("Select a state from the list above");
        statusLabel.setStyle("-fx-text-fill: gray;");
        
        // Info label
        Label infoLabel = new Label(
                "Tip: Use Ctrl+Z to undo, Ctrl+Y to redo");
        infoLabel.setStyle("-fx-text-fill: #666666; -fx-font-size: 11px;");
        
        // Layout
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(15));
        vBox.getChildren().addAll(instructionLabel, listView, statusLabel, infoLabel);
        
        Scene scene = new Scene(vBox);
        setScene(scene);
        
        // Position window
        setX(600);
        setY(100);
    }
    
    /**
     * Updates the history list with current undo history from controller.
     */
    public void updateHistory() {
        List<IMemento> history = controller.getUndoHistory();
        historyItems.clear();
        
        // Add items in reverse order (most recent first)
        for (int i = history.size() - 1; i >= 0; i--) {
            historyItems.add(history.get(i));
        }
        
        // Update status
        if (historyItems.isEmpty()) {
            statusLabel.setText("No history yet. Make some changes!");
        } else {
            statusLabel.setText("History contains " + historyItems.size() + " state(s)");
        }
    }
}
