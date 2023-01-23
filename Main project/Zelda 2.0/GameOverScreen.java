import greenfoot.*;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends World 
{
    GameOverLetters gameOverFont = new GameOverLetters();
   
    public GameOverScreen()
    {
        super(680,480,1);
        addObject(gameOverFont,340,240);
    }
   
    
}
