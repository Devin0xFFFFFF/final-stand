import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrier extends Actor
{
    public int strength;

    public Barrier()
    {
        strength = 5000;
    }

    public void act() 
    {
        damage();
    }    

    private void damage()
    {
        Actor enemy = getOneIntersectingObject(Enemy.class);
        Actor boss = getOneIntersectingObject(Boss.class);
        Actor shot = getOneIntersectingObject(EnemyShot.class);
        if(enemy != null)
        {
            strength--;
            checkImage();
        }
        else if(boss != null)
        {
            strength--;
            checkImage();
        }
        else if(shot != null)
        {
            strength -= 5;
            checkImage();
            getWorld().removeObject(shot);
        }
    }

    private void checkImage()
    {
        if(strength < 4000 && strength > 3000)
        {
            setImage("W2.png");
        }
        else if(strength < 3000 && strength > 2000)
        {
            setImage("W3.png");
        }
        else if(strength < 2000 && strength > 1000)
        {
            setImage("W4.png");
        }
        else if(strength < 1000)
        {
            setImage("W5.png");
        }
        if(strength <= 0)
        {
            getWorld().removeObject(this);
        }
    }

    public int getStrength()
    {
        return strength;
    }
}
