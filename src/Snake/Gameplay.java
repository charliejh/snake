package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JComponent implements KeyListener {

    private Frame frame;
    private Timer timer;
    private Snake snake;
    private Gem gem;
    private String nextMove = "UP";
    private int score = 0;
    private boolean gamePaused = false;
    private boolean gameOver = false;
    private boolean moveUsed = false;

    /**
     * Constructor
     */
    public Gameplay(Frame frame) {
        this.frame = frame;
        frame.addKeyListener(this);
        snake = new Snake();
        gem = new Gem(40, 10, 10, Color.white);
    }

    /**
     * Starts the game
     */
    public void start() {
        timer = new Timer(200, e -> update());
        timer.start();
    }

    /**
     * update() is called after the delay on the timer
     */
    private void update() {
        if (!gamePaused && !gameOver) {
            nextMove();
            moveUsed = false;
        }
        frame.repaint();
    }

    /**
     * Performs the next move after the timer has ticked
     * If the snake gets the gem, the gem is moved, the players score increments and the snake is extended
     */
    private void nextMove() {
        for (int i = snake.getSize() - 1; i > -1; i--) {
            if (i == 0) {
                if (snake.getBlock(i).getX() == gem.getX() && snake.getBlock(i).getY() == gem.getY()) {
                    moveGem();
                    score++;
                    snake.extendSnake();
                }
                switch (nextMove) {
                    case "UP":
                        if (snake.getBlock(i).getY() - 1 < 0) {
                            snake.getBlock(i).move(snake.getBlock(i).getX(), 19);
                        }
                        else {
                            snake.getBlock(i).move(snake.getBlock(i).getX(), snake.getBlock(i).getY() - 1);
                        }
                        break;
                    case "DOWN":
                        if (snake.getBlock(i).getY() + 1 > 19) {
                            snake.getBlock(i).move(snake.getBlock(i).getX(), 0);
                        }
                        else {
                            snake.getBlock(i).move(snake.getBlock(i).getX(), snake.getBlock(i).getY() + 1);
                        }
                        break;
                    case "LEFT":
                        if (snake.getBlock(i).getX() - 1 < 0) {
                            snake.getBlock(i).move(19, snake.getBlock(i).getY());
                        }
                        else {
                            snake.getBlock(i).move(snake.getBlock(i).getX() - 1, snake.getBlock(i).getY());
                        }
                        break;
                    case "RIGHT":
                        if (snake.getBlock(i).getX() + 1 > 19) {
                            snake.getBlock(i).move(0, snake.getBlock(i).getY());
                        }
                        else {
                            snake.getBlock(i).move(snake.getBlock(i).getX() + 1, snake.getBlock(i).getY());
                        }
                        break;
                }
            }
            else {
                snake.getBlock(i).move(snake.getBlock(i - 1).getX(), snake.getBlock(i - 1).getY());
            }
        }
        for (int j = 1; j < snake.getSize(); j++) {
            if (snake.getBlock(0).getX() == snake.getBlock(j).getX() && snake.getBlock(0).getY() == snake.getBlock(j).getY()) {
                gameOver = true;
            }
        }

    }

    /**
     * Moves the gem to a random location on the board apart from any square that is occupied by the snake
     */
    private void moveGem() {
        Random randomX = new Random();
        int x = randomX.nextInt(20);
        Random randomY = new Random();
        int y = randomY.nextInt(20);
        boolean canMove = true;
        for (int i = 0; i < snake.getSize(); i++) {
            if (snake.getBlock(i).getX() == x && snake.getBlock(i).getY() == y) {
                canMove = false;
            }
        }
        if (!canMove) moveGem();
        else { gem.move(x, y); }
    }

    /**
     * Paint methods to paint to the component
     */
    public void paintComponent(Graphics graphics) {
        drawBackground(graphics);
        drawScoreBoard(graphics);
        gem.draw(graphics);
        snake.draw(graphics);
        if (gamePaused) {
            drawPauseScreen(graphics, "Paused!", 290);
        }
        if (gameOver) {
            drawPauseScreen(graphics, "Game Over!", 240);
        }
    }

    /**
     * Draws the background
     */
    private void drawBackground(Graphics graphics) {
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < 840; i += 40) {
                graphics.drawLine(0, i, 800, i);
            }
            for (int i = 0; i < 840; i += 40) {
                graphics.drawLine(i, 0, i, 800);
            }
    }

    /**
     * Draws the scoreboard
     */
    private void drawScoreBoard(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 25));
        graphics.drawString("Snake", 910, 40);
        graphics.drawString("By Charlie Harris", 840, 80);
        graphics.drawString("Score: " + score, 900, 140);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
        graphics.drawString("P to Pause Game", 863, 350);
        graphics.drawString("R to Resume Game", 850, 400);
    }

    /**
     * Draw the pause screen
     */
    private void drawPauseScreen(Graphics graphics, String text, int x) {
        graphics.setColor(new Color(0,0,0, 120));
        graphics.fillRect(0,0,20 * 40, 20 * 40);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.BOLD, 50));
        graphics.drawString(text, x, 400);
    }


    @Override
    public void keyTyped(KeyEvent e) { }
    /**
     * Change the string value of nextMove which determines where the head of the snake will move
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gamePaused && !gameOver && !moveUsed) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if (nextMove.equals("LEFT") || nextMove.equals("RIGHT")) {
                        nextMove = "UP";
                        moveUsed = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (nextMove.equals("LEFT") || nextMove.equals("RIGHT")) {
                        nextMove = "DOWN";
                        moveUsed = true;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (nextMove.equals("UP") || nextMove.equals("DOWN")) {
                        nextMove = "LEFT";
                        moveUsed = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (nextMove.equals("UP") || nextMove.equals("DOWN")) {
                        nextMove = "RIGHT";
                        moveUsed = true;
                    }
                    break;
                case KeyEvent.VK_P:
                    gamePaused = true;
                    break;
            }
        }
        if (gamePaused) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                gamePaused = false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { }

}
