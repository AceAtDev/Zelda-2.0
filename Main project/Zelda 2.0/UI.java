import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UI extends Actor
{
    
    int xPos = 50;
    int yPos = 50;
    private boolean canFollow = true;
    private boolean firstCall = true;
    protected Link player = null;
    
    /**
     * Act - do whatever the UI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public UI (int xPos, int yPos, Link player) // pass in the location of the ui
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.player = player;
    }
    
    public UI()
    {
        
    }
    
    public void positionUpdater()
    {
        if(!canFollow) {return;}
        if(firstCall)
        {   
            setLocation(getX() + xPos, getY() + yPos);
            firstCall = false;
        }
        
        this.setLocation(getX() + player.getX(), getY() + player.getY());
    }
}
