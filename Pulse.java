import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pulse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pulse extends Projectiles
{
    public int caliber;

    public Pulse(int caliber)
    {
        this.caliber = caliber;
        enemy = false;
        int direction = Greenfoot.getRandomNumber(10)+175;
        double velocity = 20.0;

        addForce(new Vector(direction, velocity));
    }

    public void act() 
    {
        movement();
        collisions(2,0);
    }    

    public int getCaliber()
    {
        return caliber;
    }
}
