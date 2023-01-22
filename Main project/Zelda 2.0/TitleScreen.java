import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World

{
    TitleLetters titleFont = new TitleLetters();
    uiButtons playButton = new uiButtons("Button Image.png", 2);
    uiButtons creditsButton = new uiButtons("Button Image.png", 3);
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
       super(680, 480, 1); 
       addObject(new FadeOverlay(),getWidth()/2,getHeight()/2);
       prepareTitleScreen();  
        
    }
    private void prepareTitleScreen(){
         
        addObject(titleFont,340,240);
        addObject(playButton,240,355);
        addObject(creditsButton,240,385);
        
    }
    
}
