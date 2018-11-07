package snake.gameobjects;

import snake.Constants;
import snake.Direction;

import java.awt.*;


public class Block {

    private int x, y;
    private Color blockColor;

    public Block(int pX, int pY, Color pBlockColor) {
        this.x = pX;
        this.y = pY;
        this.blockColor = pBlockColor;
    }

    public void move(Direction pDirection) {
        switch (pDirection) {
            case DOWN:
                this.y++;
                break;
            case UP:
                this.y--;
                break;
            case LEFT:
                this.x--;
                break;
            case RIGHT:
                this.x++;
                break;
        }
    }

    public void moveToOtherBlock(Block block) {
        this.x = block.x;
        this.y = block.y;
    }

    public void draw(Graphics pGraphics) {
        pGraphics.setColor(blockColor);
        pGraphics.fillRect(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
        pGraphics.setColor(Color.BLACK);
        pGraphics.drawRect(x * Constants.BLOCK_SIZE, y * Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
