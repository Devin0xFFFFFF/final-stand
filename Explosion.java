import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private int count;

    public Explosion()
    {
        count = 0;    
    }

    public void act() 
    {
        explode();
    }    

    public void explode()
    {
        count++;
        if(count == 2)
        {
            Greenfoot.playSound("Grenade_small.wav");
            GreenfootImage img = getImage();
            img.scale(80,80);
        }
        if(count == 4)
        {
            GreenfootImage img = getImage();
            img.scale(100,100);
        }
        if(count == 6)
        {
            GreenfootImage img = getImage();
            img.scale(120,120);
        }
        if(count == 8)
        {
            GreenfootImage img = getImage();
            img.scale(140,140);
        }
        if(count == 10)
        {
            GreenfootImage img = getImage();
            img.scale(160,160);
        }
        if(count == 12)
        {
            GreenfootImage img = getImage();
            img.scale(140,140);
        }
        if(count == 14)
        {
            GreenfootImage img = getImage();
            img.scale(120,120);
        }
        if(count == 16)
        {
            GreenfootImage img = getImage();
            img.scale(80,80);
        }
        if(count == 18)
        {
            GreenfootImage img = getImage();
            img.scale(40,40);
        }
        if(count >= 20)
        {
            getWorld().removeObject(this);
        }
    }
}
