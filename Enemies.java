import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemies extends SmoothMover
{
    public int life;
    public String img;
    public int counter;
    public static final int ROC = 15;
    public int picNum;
    public int transparency;
    public boolean dead;
    public boolean boss;
    public int deathCounter;
    public boolean DL;
    public int currentImage;
    public int clockValue;
    public double speed;
    public double maxSpeed;
    public int pointValue;
    public boolean hit;

    public Enemies()
    {

    }

    public void act() 
    {
        // Add your action code here.
    }    

    public void processes()
    {
        if(!dead)
        {
            if(!hit)
            {
                movement();
            }
            damage();
        }
        if(life <= 0)
        {
            if(!boss)
            {
                dead = true;
                setImage(img+5+".png");
            }
            else
            {
                dead = true;
                if(!DL)
                {
                    deathSequence(1);
                }
                else
                {
                    deathSequence(2);
                }
            }

            GreenfootImage image = getImage();
            transparency -= 5;
            image.setTransparency(transparency);

            if(transparency <= 5)
            {
                GameWorld world = (GameWorld) getWorld();
                world.increaseKills();
                world.increasePoints(pointValue);
                getWorld().removeObject(this);
            }
        }
    }

    private void movement()
    {
        if(!atBarrier())
        {
            if(getX() < 600)
            {
                move();
            }
            else
            {
                getMovement().setDirection(locateHero());
                move();
            }
        }
        changeImages();

    }

    public void damage()
    {
        Actor bullet = getOneIntersectingObject(Bullet.class);
        Actor explosion = getOneIntersectingObject(Explosion.class);
        Actor pulse = getOneIntersectingObject(Pulse.class);
        Actor flame = getOneIntersectingObject(Flame.class);
        Actor fire = getOneIntersectingObject(Fire.class);
        Actor unitBullet = getOneIntersectingObject(UnitWeapon.class);
        if(bullet != null)
        {
            GameWorld world = (GameWorld) getWorld();
            life -= world.getCaliber();
            setImage(img+4+".png");
            getWorld().removeObject(bullet);
            hit = true;
        }
        else if(explosion != null)
        {
            GameWorld world = (GameWorld) getWorld();
            life -= world.getCaliber();
            setImage(img+4+".png");
            hit = true;
        }
        else if(pulse != null || flame != null)
        {
            GameWorld world = (GameWorld) getWorld();
            life -= world.getCaliber();
            setImage(img+4+".png");
            hit = true;
        }
        else if(unitBullet != null)
        {
            life -= 1;
            setImage(img+4+".png");
            getWorld().removeObject(bullet);
            hit = true;
        }
        else if(fire != null)
        {
            life--;
        }
        else
        {
            hit = false;
        }
    }

    public void changeImages()
    {
        counter++;
        if(counter == ROC)
        {
            setImage(img+picNum+".png");
            picNum++;
            counter = 0;
        }
        if(picNum > 2)
        {
            picNum = 1;
        }
    }

    public boolean inWorld()
    {
        if(getX() > 0)
        {
            return true;
        }
        return false;
    }

    public void deathSequence(int version)
    {
        deathCounter++;
        if(version == 1)
        {
            if(currentImage < 9)
            {
                if(deathCounter == clockValue)
                {
                    setImage(img+currentImage+".png");
                    currentImage ++;
                    clockValue += 50;
                }
            }
        }
        else if(version == 2)
        {
            if(currentImage < 13)
            {
                if(deathCounter == clockValue)
                {
                    setImage(img+currentImage+".png");
                    currentImage ++;
                    clockValue += 20;
                }
            }
        }
    }

    private int locateHero() 
    { 
        Hero h = (Hero)(getWorld().getObjects(Hero.class).get(0)); 
        double rotation = Math.atan2(h.getY() - getY(), h.getX() - getX()); 
        rotation = Math.toDegrees(rotation); 
        return (int)rotation;
    }

    private boolean atBarrier()
    {
        Actor barrier = getOneIntersectingObject(Barrier.class);
        if(barrier != null)
        {
            return true;
        }
        return false;
    }
}
