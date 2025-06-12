package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;


/**
 * Contains data for the Player's Sprites and attibutes
 */
public class Player extends Entity 
{
    GamePanel gp;
    KeyHandler keyH;
    double change;

    /**
     * Contructor for the Player class
     * @param gp the gamePanel that our player will exist within
     * @param keyH The keyHandler object that out player will use 
     */
    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        direction = "right";
    }


    /**
     * Loads out pixel art PNGs fron the player folder onto our variables that we inherited fom the entity class, this method will just be called in the player constructor, which is then called in]
     * the main game panel
     */
    public void getPlayerImage()
    {
        //Uses ImageIO class read method which can throw IO Exeption
        try
        {
            fe1Up = ImageIO.read(getClass().getResourceAsStream("/player/FE1Up.png"));
            fe1TopLeft = ImageIO.read(getClass().getResourceAsStream("/player/FE1TopLeft.png"));
            fe1Left = ImageIO.read(getClass().getResourceAsStream("/player/FE1Left.png"));
            fe1BottomLeft = ImageIO.read(getClass().getResourceAsStream("/player/FE1BottomLeft.png"));
            fe1Down = ImageIO.read(getClass().getResourceAsStream("/player/FE1Down.png"));
            fe1BottomRight = ImageIO.read(getClass().getResourceAsStream("/player/FE1BottomRight.png"));
            fe1Right = ImageIO.read(getClass().getResourceAsStream("/player/FE1Right.png"));
            fe1TopRight = ImageIO.read(getClass().getResourceAsStream("/player/FE1TopRight.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("error");
        }
    }





    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        maxSpeed = 7;

        velY = 0;
        velX = 0;
        acc = 0.1;

        if (maxSpeed % 2 == 0)
        {
            change = (maxSpeed / 2);
        }
        else
        {
            change = (maxSpeed / 2) + 0.5;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        //make a stsytem to draw the correct sprite to the screen based off of velocity
        if (velX >= 0 && velY <= change && velY >= -change) 
        {
            image = fe1Right;
        }
        else if (velX <= 0 && velY <= change && velY >= -change)
        {
            image = fe1Left;
        }
        else if (velY <= 0 && velX <= change && velX >= -change)
        {
            image = fe1Up;
        }
        else 
        {
            image = fe1Down;
        }
        

        g2.drawImage(image, x, y, gp.TILE_SIZE + 16, gp.TILE_SIZE + 16, null);
    }

    /**
     * A player object's update method will be called insside of the main game loop
     * This is just copy pasted code from over the gamePanel class that we made earlier
     */
    public void update()
    {
        if (keyH.upPressed)
        {
            velY -= acc;
        }
        if (keyH.downPressed)
        {
            velY += acc;
        }
        
        if (keyH.leftPressed)
        {
            velX -= acc;
        }
        if (keyH.rightPressed)
        {
            velX += acc;
        }

        double speed = Math.sqrt(velX * velX + velY * velY);
        if (speed > maxSpeed)
        {
            velX = (velX / speed) * maxSpeed;
            velY = (velY / speed) * maxSpeed;
        }

        x += velX;
        y += velY;
    }
}
