import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UnitGrenade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UnitGrenade extends Projectiles
{
    private Vector GRAVITY = new Vector(90,0.2);
    public UnitGrenade()
    {
        int angle = Greenfoot.getRandomNumber(50)+200;
        double velocity = Greenfoot.getRandomNumber(10)/2+5.0;
        addForce(new Vector(angle,velocity));
    }

    public void act() 
    {
        move();
        drop();
    }    

    private void drop()
    {
        addForce(GRAVITY);
        if(getMovement().getLength()>=10)
        {
            explode();
        }
    }

    private void explode()
    {
        getWorld().addObject(new Explosion(),getX(),getY());
        getWorld().removeObject(this);
    }
}
