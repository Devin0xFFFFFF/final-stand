import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyShot extends Projectiles
{
    public int type;
    public EnemyShot(int type)
    {
        if(type == 1)
        {
            setImage("bomb.png");
            Greenfoot.playSound("Small Fireball.wav");
        }
        else if(type == 2)
        {
            setImage("DL_rocket.png");
            Greenfoot.playSound("Small Fireball.wav");
            setRotation(0);
        }
        enemy = true;
        this.type = type;
        int direction = 0;
        double velocity = 20.0;
        addForce(new Vector(direction, velocity));
    }

    public void act() 
    {
        movement();
        collisions(3,0);
    }    
}
