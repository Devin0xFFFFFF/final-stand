import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Enemies
{
    private int type;
    private int counter;
    private int ROF;
    public Enemy(int type)
    {
        double speed = 0;
        if(type == 0)
        {
            life = 10;
            speed = 0.3;
            img = "Z";
            pointValue = 10;
        }
        if(type == 1)
        {
            life = 5;
            speed = 1.8;
            img = "I";
            pointValue = 50;
        }
        if(type == 2)
        {
            life = 30;
            speed = 0.5;
            img = "D";
            pointValue = 100;
        }
        if(type == 3)
        {
            life = 50;
            speed = 0.1;
            img = "WI";
            ROF = Greenfoot.getRandomNumber(300)+200;
            counter = 0;
            pointValue = 300;
        }
        if(type == 4)
        {
            life = 100;
            speed = 0.3;
            img = "M";
            pointValue = 500;
        }
        this.type = type;
        this.speed  = speed;

        addForce(new Vector(0,speed));

        counter = 0;
        picNum = 1;
        dead = false;
        hit = false;
        boss = false;
        currentImage = 5;
        clockValue = 1;
        GreenfootImage image = getImage();
        transparency = image.getTransparency();
    }

    public void act() 
    {
        processes();
        if(type == 3)
        {
            shoot();
        }
    }    

    private void shoot()
    {
        if(!dead)
        {
            counter++;
            if(counter >= ROF)
            {
                setImage("WI3.png");
                Greenfoot.playSound("Small Fireball.wav");
                getWorld().addObject(new EnemyShot(1),getX()+30,getY());
                ROF = Greenfoot.getRandomNumber(300)+200;
                counter = 0;
            }
        }
    }
}
