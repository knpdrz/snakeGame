package snakePack;

import java.awt.Rectangle;

public class Tile extends Rectangle {
    private Field field;
    
    public Tile(int x, int y, int width, int height, Field field) {
        super(x, y, width, height);
        this.field = field; 
    }

    public void setField(Field field) { this.field = field; }

    public Field getField() { return field; }
}
