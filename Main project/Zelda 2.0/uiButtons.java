import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class uiButtons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class uiButtons extends Actor
{
    public World screenChange;
    
    public uiButtons(String Image, World screenChange){
        setImage(new GreenfootImage(Image));
        this.screenChange = screenChange;
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(this)){ // If Object Clicked, Will 
            Greenfoot.setWorld(this.screenChange);
        }
    }
}
