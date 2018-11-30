import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Unit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Unit extends Placeables
{
    private int type;
    private int timer;
    private int shoot;
    private int variance;
    public Unit(int type)
    {
        variance = 0;
        if(type == 0)
        {
            setImage("infantry_support.png");
            variance = 100;
        }
        else if(type == 1)
        {
            setImage("sniper_support.png");
            variance = 300;
        }
        else if(type == 2)
        {
            setImage("gturret_support.png");
            variance = 500;
        }
        shoot = Greenfoot.getRandomNumber(variance)+100;
        this.type = type; 
        timer = 0;
    }

    public void act() 
    {
        shoot();
        damage();
    }    

    private void shoot()
    {
        timer++;
        if(timer >= shoot)
        {
            if(type == 0)
            {
                shootBullet();
                Greenfoot.playSound("assault_fire.wav");
            }
            else if(type == 1)
            {
                getWorld().addObject(new Bullet(1,5,3),getX()-25,getY()-13);
                Greenfoot.playSound("sniper_fire.wav");
            }
            else
            {
                shootGrenade();
                Greenfoot.playSound("Tank Firing.wav");
            }
            timer = 0;
            shoot = Greenfoot.getRandomNumber(variance)+100;
        }
    }

    private void shootBullet()
    {
        getWorld().addObject(new UnitWeapon(type),getX()-25,getY()-13);
    }

    private void shootGrenade()
    {
        getWorld().addObject(new UnitGrenade(),getX()-70,getY()-60);
    }

    private void damage()
    {
        Actor enemy = getOneIntersectingObject(Enemy.class);
        Actor boss = getOneIntersectingObject(Boss.class);
        if(enemy != null || boss != null)
        {
            getWorld().removeObject(this);
        }
    }
}
