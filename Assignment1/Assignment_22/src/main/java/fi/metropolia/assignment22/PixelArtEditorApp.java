package fi.metropolia.assignment22;

import fi.metropolia.assignment22.command.Command;
import fi.metropolia.assignment22.command.GenerateCodeCommand;
import fi.metropolia.assignment22.command.MoveCursorDownCommand;
import fi.metropolia.assignment22.command.MoveCursorLeftCommand;
import fi.metropolia.assignment22.command.MoveCursorRightCommand;
import fi.metropolia.assignment22.command.MoveCursorUpCommand;
import fi.metropolia.assignment22.command.TogglePixelCommand;
import fi.metropolia.assignment22.model.PixelEditorModel;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 8×8 pixel art editor using the Command pattern for cursor moves, toggle, and code generation.
 */
public class PixelArtEditorApp extends Application {

    private static final int CELL = 40;

    private final PixelEditorModel model = new PixelEditorModel();
    private final CommandInvoker invoker = new CommandInvoker();

    private Rectangle[][] cells;

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setAlignment(Pos.CENTER);

        cells = new Rectangle[PixelEditorModel.GRID_SIZE][PixelEditorModel.GRID_SIZE];
        for (int r = 0; r < PixelEditorModel.GRID_SIZE; r++) {
            for (int c = 0; c < PixelEditorModel.GRID_SIZE; c++) {
                Rectangle rect = new Rectangle(CELL, CELL);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(1);
                cells[r][c] = rect;
                grid.add(rect, c, r);
            }
        }

        Label help = new Label(
                "Arrows: move cursor   |   Space: toggle pixel   |   Button: print Java array to console"
        );
        help.setWrapText(true);
        help.setStyle("-fx-text-fill: #cccccc;");

        Button createCode = new Button("Create Code (console)");
        createCode.setOnAction(e -> {
            invoker.execute(new GenerateCodeCommand(model));
        });

        VBox root = new VBox(12, help, grid, createCode);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));
        root.setStyle("-fx-background-color: #1a1a1a;");

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::handleKey);
        root.setFocusTraversable(true);
        stage.setTitle("Pixel Art Editor (8×8) — Assignment 22");
        stage.setScene(scene);
        stage.show();

        refreshView();
        javafx.application.Platform.runLater(() -> scene.getRoot().requestFocus());
    }

    private void handleKey(KeyEvent event) {
        Command command = switch (event.getCode()) {
            case UP -> new MoveCursorUpCommand(model);
            case DOWN -> new MoveCursorDownCommand(model);
            case LEFT -> new MoveCursorLeftCommand(model);
            case RIGHT -> new MoveCursorRightCommand(model);
            case SPACE -> new TogglePixelCommand(model);
            default -> null;
        };
        if (command != null) {
            invoker.execute(command);
            refreshView();
            event.consume();
        }
    }

    private void refreshView() {
        int cr = model.getCursorRow();
        int cc = model.getCursorCol();
        for (int r = 0; r < PixelEditorModel.GRID_SIZE; r++) {
            for (int c = 0; c < PixelEditorModel.GRID_SIZE; c++) {
                Rectangle rect = cells[r][c];
                boolean on = model.getCell(r, c) == 1;
                rect.setFill(on ? Color.rgb(0, 220, 140) : Color.rgb(45, 45, 48));
                boolean cursorHere = r == cr && c == cc;
                rect.setStroke(cursorHere ? Color.YELLOW : Color.BLACK);
                rect.setStrokeWidth(cursorHere ? 3 : 1);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
