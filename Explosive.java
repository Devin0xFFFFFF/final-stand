import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosive extends Placeables
{
    private int type;
    private int timer;
    private int activate;
    public Explosive(int type)
    {
        this.type = type; 
        timer = 0;
        activate = Greenfoot.getRandomNumber(100)+50;
        if(type == 0)
        {
            setImage("grenade.png");
        }
        else if(type == 1)
        {
            setImage("land_mine.png");
        }
        if(type == 2)
        {
            setImage("c4_charge.png");
        }
    }

    public void act() 
    {
        runVersion();
    }    

    private void runVersion()
    {
        timer++;
        if(type == 0)
        {
            if(timer >= activate)
            {
                explode();
            }
        }
        else if(type == 1)
        {
            Actor enemy = getOneIntersectingObject(Enemy.class);
            Actor boss = getOneIntersectingObject(Boss.class);
            if(enemy != null || boss != null)
            {
                explode();
            }
        }
        if(type == 2)
        {
            if(timer >= 100 && Greenfoot.mouseClicked(this))
            {
                explode();
            }
        }
    }

    private void explode()
    {
        getWorld().addObject(new Explosion(),getX(),getY());
        getWorld().removeObject(this);
    }
}
