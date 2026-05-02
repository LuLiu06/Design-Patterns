package fi.metropolia.assignment22.command;

import fi.metropolia.assignment22.model.PixelEditorModel;

public class MoveCursorRightCommand implements Command {

    private final PixelEditorModel model;

    public MoveCursorRightCommand(PixelEditorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveCursor(0, 1);
    }
}
