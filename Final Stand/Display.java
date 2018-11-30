import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Display here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Display extends Actor
{
    public Display (String text)
    {
        GreenfootImage img = new GreenfootImage(300,30);
        img.setFont(new Font("Comic Sans MS", 12));
        img.drawString(text,5,25);
        setImage(img); 
    }

    public void setValue (String text)
    {
        GreenfootImage img = getImage();
        img.clear();
        img.setFont(new Font("Comic Sans MS", 12));
        img.drawString(text,5,25);
    }      
}
