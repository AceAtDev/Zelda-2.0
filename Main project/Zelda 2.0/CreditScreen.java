import greenfoot.*;

/**
 * Write a description of class CreditScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreditScreen extends World

{
 uiButtons menuButton = new uiButtons("Button Image.png",new TitleScreen());
    /**
     * Constructor for objects of class CreditScreen
     */
    public CreditScreen()
    {
        super(680,480,1);
        prepareCreditScreen();
    }
    public void prepareCreditScreen(){
    CreditScreenLetters creditFont = new CreditScreenLetters();
    //addObject(creditFont,340,240);
    //addObject(menuButton,90,440);
    }
    public void act(){
    }

   
}
