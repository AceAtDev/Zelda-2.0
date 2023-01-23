import greenfoot.*;

/**
 * Write a description of class WinScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinScreen  extends World
{
    // instance variables - replace the example below with your own
  WinScreenLetters winScreenFont = new WinScreenLetters();
    public WinScreen()
    {
        super(680,480,1);
        addObject(winScreenFont,340,240);
    }

  
}
