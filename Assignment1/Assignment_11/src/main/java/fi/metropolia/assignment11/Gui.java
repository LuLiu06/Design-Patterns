package fi.metropolia.assignment11;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main GUI class for the Memento Pattern Example.
 * 
 * Features:
 * - Three colored rectangles that change color on click
 * - A checkbox
 * - Undo functionality (Ctrl+Z)
 * - Redo functionality (Ctrl+Y)
 * - History window showing all saved states
 */
public class Gui extends Application {
    
    private Controller controller;
    private ColorBox colorBox1;
    private ColorBox colorBox2;
    private ColorBox colorBox3;
    private CheckBox checkBox;
    private HistoryWindow historyWindow;
    
    @Override
    public void start(Stage stage) {
        controller = new Controller(this);
        
        Insets insets = new Insets(10, 10, 10, 10);
        
        // Create three ColorBoxes
        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);
        
        // Create a CheckBox
        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);
        checkBox.setOnAction(event -> {
            controller.setIsSelected(checkBox.isSelected());
        });
        
        // Add the ColorBoxes to an HBox
        HBox colorBoxContainer = new HBox(10);
        colorBoxContainer.getChildren().addAll(
                colorBox1.getRectangle(), 
                colorBox2.getRectangle(), 
                colorBox3.getRectangle()
        );
        colorBoxContainer.setPadding(insets);
        
        // Instructions label
        Label label = new Label(
                "Press Ctrl+Z to undo, Ctrl+Y to redo\n" +
                "Click on boxes to change colors"
        );
        label.setPadding(insets);
        
        // History window button
        Button historyButton = new Button("Open History Window");
        historyButton.setOnAction(event -> openHistoryWindow());
        historyButton.setPadding(new Insets(5, 15, 5, 15));
        
        // Undo/Redo buttons (optional, in addition to keyboard shortcuts)
        Button undoButton = new Button("Undo (Ctrl+Z)");
        undoButton.setOnAction(event -> controller.undo());
        
        Button redoButton = new Button("Redo (Ctrl+Y)");
        redoButton.setOnAction(event -> controller.redo());
        
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(insets);
        buttonBox.getChildren().addAll(undoButton, redoButton, historyButton);
        
        // Main layout
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(colorBoxContainer, checkBox, label, buttonBox);
        
        // Create scene and set up key handlers
        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown()) {
                if (event.getCode() == KeyCode.Z) {
                    // Ctrl+Z: Undo
                    System.out.println("Undo key combination pressed (Ctrl+Z)");
                    controller.undo();
                } else if (event.getCode() == KeyCode.Y) {
                    // Ctrl+Y: Redo
                    System.out.println("Redo key combination pressed (Ctrl+Y)");
                    controller.redo();
                }
            }
        });
        
        stage.setScene(scene);
        stage.setTitle("Memento Pattern - Undo/Redo/History Demo");
        stage.show();
        
        // Focus on scene for key events
        vBox.requestFocus();
    }
    
    /**
     * Opens or focuses the history window.
     */
    private void openHistoryWindow() {
        if (historyWindow == null || !historyWindow.isShowing()) {
            historyWindow = new HistoryWindow(controller);
            historyWindow.show();
        } else {
            historyWindow.requestFocus();
        }
    }
    
    /**
     * Updates the GUI after state restoration.
     * Called by the controller when undo/redo/restore occurs.
     */
    public void updateGui() {
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
