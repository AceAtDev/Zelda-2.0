import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class uiButtons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class uiButtons extends Actor
{
    public int screenChange;
    
    public uiButtons(String Image, int screenChange){
        setImage(new GreenfootImage(Image));
        this.screenChange = screenChange;
    }
    
    public void act()
    {
        switch(screenChange){
            case 1:
        if(Greenfoot.mouseClicked(this)){ 
            Greenfoot.setWorld(new TitleScreen());
        }
            break;
            case 2:
            if(Greenfoot.mouseClicked(this)){ 
            Greenfoot.setWorld(new RandomlyGeneratingDungeon());
        }
            break;
            case 3:
            if(Greenfoot.mouseClicked(this)){ 
            Greenfoot.setWorld( new CreditScreen());
        }
        }
    }
}
