package fi.metropolia.assignment22.command;

import fi.metropolia.assignment22.model.PixelEditorModel;

public class TogglePixelCommand implements Command {

    private final PixelEditorModel model;

    public TogglePixelCommand(PixelEditorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.togglePixelAtCursor();
    }
}
