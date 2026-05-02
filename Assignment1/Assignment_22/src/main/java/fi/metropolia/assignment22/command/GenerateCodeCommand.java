package fi.metropolia.assignment22.command;

import fi.metropolia.assignment22.model.PixelEditorModel;

/**
 * Prints a Java {@code int[][]} literal for the current grid to the console.
 */
public class GenerateCodeCommand implements Command {

    private final PixelEditorModel model;

    public GenerateCodeCommand(PixelEditorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("int[][] pixelArt = {\n");
        int n = PixelEditorModel.GRID_SIZE;
        for (int r = 0; r < n; r++) {
            sb.append("    {");
            for (int c = 0; c < n; c++) {
                sb.append(model.getCell(r, c));
                if (c < n - 1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
            if (r < n - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("};");
        System.out.println(sb);
    }
}
