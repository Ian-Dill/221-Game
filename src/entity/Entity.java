package entity;

import java.awt.image.BufferedImage;

/**
 * The parent class for all entitites: NPC, Player
 */
public class Entity 
{
    public int x;
    public int y;

    public int maxSpeed;

    public double velY;

    public double velX;

    public double acc;

    public BufferedImage fe1Up, fe1TopLeft, fe1Left, fe1BottomLeft, fe1Down, fe1BottomRight, fe1Right, fe1TopRight;
    public String direction;
}

