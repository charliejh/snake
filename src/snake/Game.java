package snake;

import javax.swing.*;

public class Game extends JFrame {

    private Gameplay gameplay;

    public Game(int pWidth, int pHeight, Gameplay pGameplay) {
        setTitle("snake - Charlie Harris");
        setSize(pWidth, pHeight);
        getContentPane().setBackground(Constants.BACKGROUND_COLOUR);
        this.gameplay = pGameplay;
        add(gameplay);
        addKeyListener(gameplay);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startGame() {
        gameplay.start();
    }

}
