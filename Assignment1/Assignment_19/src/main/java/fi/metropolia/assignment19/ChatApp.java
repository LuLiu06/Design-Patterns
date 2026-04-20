package fi.metropolia.assignment19;

import fi.metropolia.assignment19.client.ChatClientController;
import fi.metropolia.assignment19.mediator.ChatMediator;
import fi.metropolia.assignment19.mediator.ChatMediatorImpl;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/**
 * Launches three (or more) chat client windows on the same machine.
 * All communication is routed through a single {@link ChatMediator}.
 * <p>
 * On macOS, opening multiple {@link Stage}s and moving them before the peer is
 * fully initialized can crash AppKit (NSTrackingRect). We therefore show each
 * window first, then position it on the next UI pulse, and stagger openings
 * slightly so the window manager can activate the app cleanly.
 */
public class ChatApp extends Application {
    
    private static final List<String> DEFAULT_USERS = List.of("Alice", "Bob", "Charlie");
    private static final Duration STAGGER = Duration.millis(200);
    
    @Override
    public void start(Stage primaryStage) {
        ChatMediator mediator = new ChatMediatorImpl();
        scheduleOpenWindow(0, primaryStage, mediator);
    }
    
    private void scheduleOpenWindow(int index, Stage primaryStage, ChatMediator mediator) {
        if (index >= DEFAULT_USERS.size()) {
            return;
        }
        
        PauseTransition pause = new PauseTransition(index == 0 ? Duration.ZERO : STAGGER);
        pause.setOnFinished(e -> {
            Stage stage = index == 0 ? primaryStage : new Stage();
            String user = DEFAULT_USERS.get(index);
            ChatClientController controller = new ChatClientController(user, mediator, DEFAULT_USERS);
            controller.show(stage);
            
            final double x = 40 + index * 50;
            final double y = 100;
            Platform.runLater(() -> {
                stage.setX(x);
                stage.setY(y);
            });
            
            scheduleOpenWindow(index + 1, primaryStage, mediator);
        });
        pause.play();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
