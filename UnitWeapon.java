import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UnitWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UnitWeapon extends Projectiles
{
    public int type;
    public int caliber;
    public UnitWeapon(int type)
    {
        int direction = 0;
        double velocity = 20.0;
        if(type == 0 || type == 1)
        {
            setImage("bullet.png");
            direction = 180;
        }
        enemy = false;
        this.type = type;
        caliber = 1;
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
