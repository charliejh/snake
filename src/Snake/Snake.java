package Snake;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Block> blocks;
    private final int blockSize = 40;
    private Color headColor = new Color(100, 255, 93);
    private Color bodyColor = new Color(140, 188, 93);

    /**
     * Constructor
     * Adds blocks to the blocks ArrayList and places the snake's starting x and y position
     */
    public Snake() {
        blocks = new ArrayList<>();
        int y = 15;
        for (int i = 0; i < 4; i++) {
            if (i == 0) blocks.add(new Block(blockSize, 1, y, headColor));
            else { blocks.add(new Block(blockSize, 1, y, bodyColor)); }
            y++;
        }
    }

    /**
     * Extends the snake by adding three extra blocks at the back
     */
    public void extendSnake() {
        for (int i = 0; i < 3; i++) {
            blocks.add(new Block(blockSize, blocks.get(blocks.size() - 1).getX(), blocks.get(blocks.size() - 1).getY(), bodyColor));
        }
    }

    /**
     * Draws the snake by calling the each blocks draw() method
     */
    void draw(Graphics graphics) {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).draw(graphics);
        }
    }

    /**
     * Getters
     */
    public int getSize() { return blocks.size(); }
    public Block getBlock(int i) { return blocks.get(i); }

}
