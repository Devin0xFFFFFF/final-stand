import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Enemies
{
    private int type;
    private int counter;
    private int ROF;
    public Boss(int type)
    {
        double speed = 0;
        if(type == 0)
        {
            life = 200;
            speed = 0.1;
            img = "S";
            pointValue = 1000;
        }
        if(type == 1)
        {
            life = 300;
            speed = 0.1;
            img = "A";
            pointValue = 3000;
        }
        if(type == 2)
        {
            life = 500;
            speed = 0.01;
            img = "B";
            pointValue = 5000;
        }
        if(type == 3)
        {
            life = 1000;
            speed = 0.01;
            img = "L";
            ROF = Greenfoot.getRandomNumber(300)+200;
            counter = 0;
            pointValue = 10000;
        }
        if(type == 4)
        {
            life = 5000;
            speed = 0.01;
            img = "DL";
            DL = true;
            pointValue = 50000;
        }
        this.type = type;
        this.speed = speed;

        addForce(new Vector(0,speed));

        counter = 0;
        picNum = 1;
        dead = false;
        hit = false;
        boss = true;
        currentImage = 5;
        clockValue = 1;
        deathCounter = 0;
        GreenfootImage image = getImage();
        transparency = image.getTransparency();
    }

    public void act() 
    {
        processes();
        if(type == 2 && inWorld())
        {
            shoot(1);
        }
        if(type == 4)
        {
            shoot(2);
        }
        if(type == 3)
        {
            shoot(3);
        }
    }    

    private void shoot(int shotType)
    {
        if(!dead)
        {
            counter++;
            if(counter >= ROF)
            {
                if(type != 3)
                {
                    setImage(img+"3.png");
                    getWorld().addObject(new EnemyShot(shotType),getX()+30,getY());
                    ROF = Greenfoot.getRandomNumber(300)+200;
                    counter = 0;
                }
                else
                {
                    getWorld().addObject(new Summon(),getX(),getY()-150);
                    getWorld().addObject(new Summon(),getX(),getY()+150);
                    ROF = Greenfoot.getRandomNumber(300)+200;
                    counter = 0;
                }
            }
        }
    }   
}
