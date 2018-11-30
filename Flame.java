import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flame extends Projectiles
{
    public int caliber;

    public Flame(int type, int caliber)
    {
        if(type == 1)
        {
            setImage("FT_F1.png");
        }
        else if(type == 2)
        {
            setImage("FT_F2.png");
        }
        enemy = false;
        this.caliber = caliber;
    }

    public void act() 
    {
        life--;
        if(life <= 0)
        {
            die();
        }
    }    

    public int getCaliber()
    {
        return caliber;
    }  
}
