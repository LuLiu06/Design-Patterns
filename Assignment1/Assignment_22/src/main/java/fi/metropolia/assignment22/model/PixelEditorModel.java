package fi.metropolia.assignment22.model;

/**
 * Receiver: holds 8×8 pixel grid and cursor position. Commands mutate this model.
 */
public class PixelEditorModel {

    public static final int GRID_SIZE = 8;

    private final int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private int cursorRow;
    private int cursorCol;

    public PixelEditorModel() {
        this.cursorRow = 0;
        this.cursorCol = 0;
    }

    public void moveCursor(int deltaRow, int deltaCol) {
        cursorRow = clamp(cursorRow + deltaRow);
        cursorCol = clamp(cursorCol + deltaCol);
    }

    public void togglePixelAtCursor() {
        grid[cursorRow][cursorCol] = 1 - grid[cursorRow][cursorCol];
    }

    public int getCell(int row, int col) {
        return grid[row][col];
    }

    public int getCursorRow() {
        return cursorRow;
    }

    public int getCursorCol() {
        return cursorCol;
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(GRID_SIZE - 1, value));
    }
}
