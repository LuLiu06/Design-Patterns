package fi.metropolia.assignment22.command;

import fi.metropolia.assignment22.model.PixelEditorModel;

public class MoveCursorUpCommand implements Command {

    private final PixelEditorModel model;

    public MoveCursorUpCommand(PixelEditorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.moveCursor(-1, 0);
    }
}
