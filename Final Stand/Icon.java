import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Icon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Icon extends Actor
{
    private String[] list;
    public int image;
    public Icon(int icon)
    {
        if(icon == 1)
        {
            setImage("grenades_icon.png");
            list = new String[] {"grenades","mines","c4"};
        }
        if(icon == 2)
        {
            setImage("rifleman_icon.png");
            list = new String[] {"rifleman","sniper","gturret"};
        }
        if(icon == 3)
        {
            setImage("airstrike_icon.png");
            list = new String[] {"airstrike","artillerystrike","nukestrike"};
        }
        if(icon == 4)
        {
            setImage("speedup_icon.png");
            list = new String[] {"speedup","quickshot","x2mags"};
        }
        image = 0;
    }

    public void act() 
    {

    }    

    public void upImage()
    {
        image++;
        if(image > 2)
        {
            image = 0;
        }
        setImage(list[image] + "_icon.png");
    }

    public GreenfootImage getThisImage()
    {
        GreenfootImage img = getImage();
        return img;
    }
}
