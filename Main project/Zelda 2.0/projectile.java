import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class projectale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class projectile extends Actor
{
    
    private int xPos;
    private int yPos;
    private int speed = 4;
    Random rand = new Random();
    
    private int deltaX;
    private int deltaY;
    private int angle;
    
    private double focusingTime = 0.2;
    private double lifeTime = 7;
    
    /**
     * Act - do whatever the projectale wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        speed = (rand.nextInt(26) - rand.nextInt(10));
        
        if(lifeTime < 0)
        {
            getWorld().removeObject(this);
            return;
        }
        lifeTime -= 0.01;
        
         turnTowards(
                    xPos,
                    yPos
                    ); // look at the player
                    
        if(focusingTime >= 0)
        {
            focusingTime -= 0.01;
            xPos = ((Link)getWorld().getObjects(Link.class).get(0)).getX() - 180;
            yPos = ((Link)getWorld().getObjects(Link.class).get(0)).getY();
            deltaX = ((Link)getWorld().getObjects(Link.class).get(0)).getX() - getX();
            deltaY = ((Link)getWorld().getObjects(Link.class).get(0)).getY() - getY();
            angle = (int) Math.atan2( deltaY, deltaX );
        }
        else
        {
            deltaX = xPos - getX();
            deltaY = yPos - getY();
            angle = (int) Math.atan2( deltaY, deltaX );
        }
        
        
        int currentX = (int) (speed * Math.cos(angle));
        int currentY = (int) (speed * Math.sin(angle));
            
        setLocation(getX()+ currentX, getY()+ currentY);
        
        
        
    }
    
    public projectile(int xPos, int yPos)
    {
       this.xPos = xPos;
       this.xPos = xPos;
       
       //System.out.println(xPos + " " + yPos);
       
       xPos -= 20;
       Greenfoot.playSound("Soul Damaged-HQ.wav");
    }
}
