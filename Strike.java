import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Strike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Strike extends Placeables
{
    private Vector GRAVITY = new Vector(90,0.2);
    private int landingSite;
    private int type;
    public Strike(int type, int location)
    {
        if(type == 0)
        {
            addForce(GRAVITY);
            landingSite = location;
        }
        else if(type == 1)
        {
            int angle = Greenfoot.getRandomNumber(50)+200;
            double velocity = Greenfoot.getRandomNumber(9)/2+8.0;
            addForce(new Vector(angle,velocity));
        }
        else if(type == 2)
        {
            addForce(GRAVITY);
            landingSite = 450;
        }
        this.type = type;
    }

    public void act() 
    {
        move();
        types();
    }  
    
    private void types()
    {
        if(type == 0 || type == 2)
        {
            drop();
        }
        else
        {
            fall();
        }
    }

    private void drop()
    {
        addForce(GRAVITY);
        if(getY() >= landingSite)
        {
            explode();
        }
    }
    
    private void fall()
    {
        setRotation(getMovement().getDirection()+270);
        addForce(GRAVITY);
        if(getMovement().getLength()>=13.0)
        {
            explode();
        }
    }

    private void explode()
    {
        if(type == 2)
        {
            int x = Greenfoot.getRandomNumber(500);
            int y = Greenfoot.getRandomNumber(300)+300;
            for(int i=0;i<50;i++)
            {
                getWorld().addObject(new Explosion(),x,y);
                x = Greenfoot.getRandomNumber(500);
                y = Greenfoot.getRandomNumber(300)+300;
            }
        }
        else
        {
            getWorld().addObject(new Explosion(),getX(),getY());
        }
        getWorld().removeObject(this);
    }
}
