import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flash extends Actor
{
    private int transparency = getImage().getTransparency();
    
    public void act() 
    {
        GreenfootImage img = getImage();
        transparency -= 10;
        img.setTransparency(transparency);
        
        if(transparency < 10)
        {
            getWorld().removeObject(this);
        }
    }    
}
