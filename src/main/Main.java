package main;

import javax.swing.JFrame;

/**
 * Cantains the main JWindow where everyuthing else is packed inside of, and is where the game itself it run from
 * @version 5/20/2025
 */

public class Main 
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();

        //method from the JFrame class telling it to system.exit when the use hits the X in the top corner of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("221");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //makes the window size to fir its subcompotents, in this case makes it the samke size as our window jawn
        window.pack();

        //not specify the location of the window, window will be set at the middle of the screeen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //runs the gameLoop
        gamePanel.startGameThread();
    }
}
