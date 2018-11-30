import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Selector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Selector extends Actor
{
    public boolean selected;
    public Selector()
    {
        selected = false;
    }

    public void act() 
    {
        findLocation();
    }    

    private void findLocation()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if(selected == true)
            {
                setLocation(mouse.getX(),mouse.getY());
            }
            else
            {
                setLocation(-100,300);
            }
        }
    }

    public boolean boundaries(int restrictions)
    {
        GameWorld world = (GameWorld) getWorld();
        if(Greenfoot.mouseClicked(this))
        {
            if(inBoundary(restrictions))
            {
                world.setCurrentLocation(getY());
                selected = false;
                return true;
            }
            else
            {
                selected = false;
            }
        }
        return false;
    }

    public boolean inBoundary(int type)
    {
        //Explosives
        if(type == 1)
        {
            if(getX() < 500 && getY() > 300)
            {
                return true;
            }
        }
        //Support Units
        if(type == 2)
        {
            if(getX() > 550 && getY() > 300)
            {
                return true;
            }
        }
        //Strikes
        if(type == 3)
        {
            if(getY() > 300)
            {
                return true;
            }
        }
        //Perks
        if(type == 4)
        {
            return true;
        }
        return false;
    }
}
