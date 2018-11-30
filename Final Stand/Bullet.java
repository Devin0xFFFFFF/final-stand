import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Projectiles
{
    public int type;
    public int caliber;

    public Bullet(int type, int caliber, int spreadValue)
    {
        if(type == 1)
        {
            setImage("bullet.png");
        }
        else if(type == 2)
        {
            setImage("plasma_bullet.png");
        }
        else if(type == 3)
        {
            setImage("gauss_bullet.png");
        }
        enemy = false;
        this.type = type;
        this.caliber = caliber;
        int direction = 0;
        if(spreadValue == 1)
        {
            direction = Greenfoot.getRandomNumber(10)+175;
        }
        else if(spreadValue == 2)
        {
            direction = Greenfoot.getRandomNumber(15)+170;
        }
        else if(spreadValue == 3)
        {
            direction = 180;
        }

        double velocity = 20.0;

        addForce(new Vector(direction, velocity));
    }

    public void act() 
    {
        movement();
        collisions(3,0);
    }    

    public int getCaliber()
    {
        return caliber;
    }
}