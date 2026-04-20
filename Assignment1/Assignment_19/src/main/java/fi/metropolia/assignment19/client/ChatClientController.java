package fi.metropolia.assignment19.client;

import fi.metropolia.assignment19.mediator.ChatClientColleague;
import fi.metropolia.assignment19.mediator.ChatMediator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for one chat client window. Handles UI events and talks only
 * to the mediator (never to other clients directly).
 */
public class ChatClientController implements ChatClientColleague {
    
    private final String username;
    private final ChatMediator mediator;
    private final List<String> allUsernames;
    
    private TextArea messageLog;
    private TextField messageField;
    private ComboBox<String> recipientCombo;
    
    public ChatClientController(String username, ChatMediator mediator, List<String> allUsernames) {
        this.username = username;
        this.mediator = mediator;
        this.allUsernames = List.copyOf(allUsernames);
    }
    
    /**
     * Builds the UI and shows the stage.
     */
    public void show(Stage stage) {
        messageLog = new TextArea();
        messageLog.setEditable(false);
        messageLog.setWrapText(true);
        messageLog.setPrefRowCount(20);
        
        Label recipientLabel = new Label("Send to:");
        recipientCombo = new ComboBox<>();
        recipientCombo.setPromptText("Choose recipient");
        List<String> others = allUsernames.stream()
                .filter(u -> !u.equals(username))
                .collect(Collectors.toList());
        recipientCombo.getItems().addAll(others);
        recipientCombo.setPrefWidth(160);
        
        messageField = new TextField();
        messageField.setPromptText("Type your message…");
        HBox.setHgrow(messageField, Priority.ALWAYS);
        
        Button sendButton = new Button("Send");
        sendButton.setDefaultButton(true);
        sendButton.setOnAction(e -> onSend());
        
        HBox inputRow = new HBox(8, recipientLabel, recipientCombo, messageField, sendButton);
        inputRow.setPadding(new Insets(8));
        inputRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        
        VBox root = new VBox(8, messageLog, inputRow);
        VBox.setVgrow(messageLog, Priority.ALWAYS);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 640, 420);
        stage.setScene(scene);
        stage.setTitle("Chat — " + username);
        stage.setOnCloseRequest(e -> mediator.unregisterClient(username));
        stage.show();
        
        mediator.registerClient(username, this);
    }
    
    private void onSend() {
        String to = recipientCombo.getSelectionModel().getSelectedItem();
        if (to == null || to.isBlank()) {
            appendLogLine("[System] Please select a recipient.");
            return;
        }
        mediator.sendMessage(username, to, messageField.getText());
        messageField.clear();
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public void appendLogLine(String formattedLine) {
        messageLog.appendText(formattedLine + System.lineSeparator());
    }
}
