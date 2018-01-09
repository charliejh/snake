package Snake;

import java.awt.*;

public class Gem {

    private int gemSize, x, y;
    Color gemColor;

    /**
     * Constructor
     */
    public Gem(int gemSize, int x, int y, Color gemColor) {
        this.gemSize = gemSize;
        this.x = x;
        this.y = y;
        this.gemColor = gemColor;
    }

    /**
     * Getters for the Gem's position
     */
    public int getX() { return x; }
    public int getY() { return y; }

    /**
     * Moves the block
     * @param x - x position of the block
     * @param y - y position of the block
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the Gem
     */
    public void draw(Graphics graphics) {
        graphics.setColor(gemColor);
        graphics.fillOval(x * gemSize, y * gemSize, gemSize, gemSize);
        graphics.setColor(new Color(0,0,0));
        graphics.drawOval(x * gemSize, y * gemSize, gemSize, gemSize);
    }

}
