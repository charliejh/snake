package Snake;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    /**
     * Constructs a non-resizable frame
     */
    public Frame() {
        setTitle("Snake - Charlie Harris");
        setSize(20 * 40 + 300, 20 * 40 + 30);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(80,80,80));
        setResizable(false);
    }

}
