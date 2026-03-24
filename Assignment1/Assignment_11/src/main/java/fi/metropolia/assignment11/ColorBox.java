package fi.metropolia.assignment11;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * ColorBox class representing a colored rectangle in the GUI.
 * Clicking the rectangle changes its color in sequence: Red -> Blue -> Yellow -> Red
 */
public class ColorBox {
    
    private int id;
    private Controller controller;
    private Rectangle rectangle;
    private Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW};
    private int colorIndex = 0;
    
    public ColorBox(int id, Controller controller) {
        this.id = id;
        this.controller = controller;
        
        rectangle = new Rectangle(100, 100);
        rectangle.setFill(colors[colorIndex]);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        
        rectangle.setOnMouseClicked(event -> {
            changeColor();
            controller.setOption(id, colorIndex);
        });
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    private void changeColor() {
        colorIndex = (colorIndex + 1) % colors.length;
        rectangle.setFill(colors[colorIndex]);
    }
    
    public void setColor(int colorIndex) {
        this.colorIndex = colorIndex;
        rectangle.setFill(colors[colorIndex]);
    }
}
