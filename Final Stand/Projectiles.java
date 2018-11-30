import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectiles extends SmoothMover
{
    public boolean explode;
    public boolean grown;
    public int size;
    public int life;
    public int count;
    public boolean enemy;
    public Projectiles()
    {
        explode = false;
        grown = false;
        life = 1;
        count = 0;
        GreenfootImage img = getImage();
        size = img.getWidth();
    }

    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    

    public void movement()
    {
        move();
        if(!enemy)
        {
            setRotation(getMovement().getDirection()+180);
        }
    }

    public void collisions(int version, int type)
    {
        if(version == 1 || version == 2)
        {
            if(atWorldEdge())
            {
                life--;
            }
            else
            {
                explode(version, type);
            }
        }
        else if(version == 3)
        {
            if(atWorldEdge())
            {
                life--;
            }
        }
        die();
    }

    public void explode(int type, int rocketType)
    {
        if(type == 1)
        {
            explosion(rocketType);
        }
        else if(type == 2)
        {
            superConduct();
        }

        if(explode == true)
        {
            emplosion();
        }
    }

    public void explosion(int fire)
    {
        Actor conduct = getOneIntersectingObject(Enemy.class);
        Actor emplode = getOneIntersectingObject(Boss.class);

        if(conduct != null || emplode != null)
        {
            getWorld().addObject(new Explosion(),getX(),getY());
            if(fire == 3)
            {
                if(Greenfoot.getRandomNumber(5)==0)
                {
                    getWorld().addObject(new Fire(),getX(),getY());
                }
            }
            getWorld().removeObject(this);
        }
    }

    public void superConduct()
    {
        Actor conduct = getOneIntersectingObject(Enemy.class);
        Actor emplode = getOneIntersectingObject(Boss.class);

        if(conduct != null || emplode != null)
        {
            explode = true;
        }
    }

    public void emplosion()
    {
        count++;
        if(count == 1)
        {
            Greenfoot.playSound ("Electric Zap.wav") ;
        }
        if(count == 2)
        {
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
            life--;
        }
    }

    public void die()
    {
        if(life <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
