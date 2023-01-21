import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World

{

    uiButtons playButton = new uiButtons("Button Image.png", new RandomlyGeneratingDungeon());
    uiButtons creditsButton = new uiButtons("Button Image.png", new CreditScreen());
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
       super(680, 480, 1); 
       prepareTitleScreen();  
        
    }
    private void prepareTitleScreen(){
        TitleLetters titleFont = new TitleLetters(); 
        addObject(titleFont,340,240);
        addObject(playButton,240,355);
        addObject(creditsButton,240,375);
        
    }
    
}
