import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverLetters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverLetters extends Actor
{
    /**
     * Act - do whatever the GameOverLetters wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public GameOverLetters()
    {
        Greenfoot.playSound("lose sound start.wav");
        Greenfoot.playSound("lose sound 2.wav");

    }
}
