import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
int gameState = 0;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
        super(680, 480, 1); 
        Greenfoot.start();
        
    }
    public void Act(){
        if(gameState == 0){
            Greenfoot.setWorld(new TitleScreen());
        }
           
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new RandomlyGeneratingDungeon());
        }
        
    }
}
