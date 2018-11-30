import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fire extends Actor
{
    private int imgCounter;
    private int imgNum;
    private int power;

    public Fire()
    {
        imgCounter = 0;
        imgNum = 1;
        power = Greenfoot.getRandomNumber(300)+200;
    }

    public void act() 
    {
        changeImages();
        powerDown();
    }    

    private void changeImages()
    {
        imgCounter++;
        if(imgCounter >= 10)
        {
            setImage("F"+imgNum+".png");
            imgNum++;
            imgCounter = 0;
        }
        if(imgNum > 4)
        {
            imgNum = 1;
        }
    }

    private void powerDown()
    {
        power--;
        if(power <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
