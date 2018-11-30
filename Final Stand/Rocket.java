import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rocket extends Projectiles
{
    public int type;
    public int caliber;

    public Rocket(int type, int caliber)
    {
        if(type < 3)
        {
            setImage("rocket.png");
        }
        else if(type == 3)
        {
            setImage("np_rocket.png");
        }
        enemy = false;
        this.type = type;

        int direction = Greenfoot.getRandomNumber(4)+178;
        double velocity = 20.0;

        addForce(new Vector(direction, velocity));
    }

    public void act() 
    {
        movement();
        collisions(1,type);
    }    
}
