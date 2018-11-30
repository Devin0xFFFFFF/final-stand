import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Summon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Summon extends Actor
{
    private int create;
    
    public Summon()
    {
        create = 100;
    }
    
    public void act() 
    {
        create--;
        if(create < 80 && create > 40)
        {
            setImage("Summon_2.png");
        }
        if(create < 40)
        {
            setImage("Summon_3.png");
        }
        if(create <= 0)
        {
            getWorld().addObject(new Enemy(2),getX(),getY());
            getWorld().removeObject(this);
        }
    }    
}
