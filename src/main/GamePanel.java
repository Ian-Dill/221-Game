package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;

import javax.swing.JPanel;



/**
 * This is the where the actual game loop will take place and be displayed
 * @extends JPanel, this class is a JPanel and has all the functionalities of one 
 * @version 5/20/2025
 */
public class GamePanel extends JPanel implements Runnable
{
    /****************************************************
    SCREEN SETTINGS
    ****************************************************/

    //The size of our tiles will be 16x16 pixels
    final int ORIGINAL_TILE_SIZE = 16; 

    //Since 16x16 is very small on high res display, we have to scale our jawn by 3x to make it appear bigger while still being 16x16 px    
    final int SCALE = 3;

    //Size of each tile, now a 48x48 tile
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    //Number of tiles wide our screen will be
    final int MAX_SCREEN_COL = 32;

    //Number if tiles tall our screen will be
    //it is currently in 16x9 aspext ratio
    final int MAX_SCREEN_ROW = 18;

    //The number of pixels wide our panel will be 768px
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;

    //Number of pixels tall our screen will be 
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    final int FPS = 60;

    /****************************************************
     FIELDS
     ****************************************************/
    
     //Thread is something you can stop and start, and once you start it, it keeps your program running until you stop it
     Thread gameThread;

     KeyHandler keyH = new KeyHandler();

     //the player for our game
     Player player = new Player(this, keyH);

   
    /**
     * Contructor for the GamePanel Class
     */
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        //This makes it so that all drawing for this panel will be done in an offscreen paiting buffer
        //So each new frame is fully drawn before its deplayed to the screen
        this.setDoubleBuffered(true);
        //adding a keyListener to out gamePanel, in this case it is our own custom keyListener object
        this.addKeyListener(keyH);
        //this allows out JPanel to get key events
        this.setFocusable(true);
    }


    /**
     * instantiates the gameThreas by passing this class to it
     */
    public void startGameThread()
    {
        gameThread = new Thread(this);
        //automaticalls calls the run method
        gameThread.start();
    }


    /**
     * Conatins the game Loop, the core of every game I think 
     */
    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        //As long as the gameThread exists, we repeat the process inside of the while loop game loop will exist as long as we
        //dont System.exit, which is done by the window's default close operation
        while (gameThread != null)
        {
            //At the beggining of the loop, check the current time 
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                //1: Update information like charactar postion
                update();

                //2: Draw the screen with updated info
                //this is just how you call paintComponent
                repaint();

                delta--;
                drawCount++;
            }


            //displaying FPS
            if (timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }
    

    /**
     * X Values increase to the Right
     * Y values increase downwards
     */
    public void update()
    {
       player.update();
    }

    /**
     * 
     * @param g an instance of the graphics class, think of it like a paintbrush
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //Casting out grahpics param to a graphics2D object which just gives us some more functionality 
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);
        g2.dispose();
    }
}
