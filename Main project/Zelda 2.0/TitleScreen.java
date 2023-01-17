import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
        super(680, 480, 1); 
       prepareTitleScreen();
        
    }
    private void prepareTitleScreen(){
        TitleLetters titleFont = new TitleLetters(); 
        addObject(titleFont,340,240);
    }
    public void act(){
      if(Greenfoot.isKeyDown("space")){ // Switches to Game Mode 
          Greenfoot.setWorld(new RandomlyGeneratingDungeon());
      }
        
    }
}
