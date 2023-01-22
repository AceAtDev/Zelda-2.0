import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.awt.Rectangle;
import java.util.List;

/**
 * The idea of this is that the enemy will move around the wrold to try and find the player.
 * Once the player in range, the enemy will start chessing him.
 * When the enemy catchs the player, the enemy and the player to get transformed to another room to fight with
 * an RPG style
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Enemy extends WorldEntity
{
    protected int speed = 1;

    
    private int xmove = 1;
    private int ymove = 1;
    
    private boolean inBattle = false; 
    
    
    
    boolean ispatrolling = true;
    boolean isPlayerInRange = false;
    
    //boolean isColliding = false;
    
    Random rand = new Random();
    private double movingTimer = 0;
    
    private boolean changedDir = false;
    private double dirTimer = 0;
    
    private int visionRange = 40 * 3; // the limit range that the enemy can follow the player while in range
    
    
    static Class[] blocks = new Class[]{Wall.class,Block.class,Lava.class,Water.class};
    //static Class opponent = Link.class;

    public Enemy(int hp, int damage, int speed)
    {
        super(10,10, hp, damage, blocks);
        this.speed = speed;
    }
    
    public void act()
    {
        if(currentHp <= 0)
        {
            this.getWorld().removeObject(this); // destory this object
        }
        
        if(inBattle == true)
        {
            setRotation(180);
            return;
        }
        
        
        
        patrol();

    }

    // Looking for the player
    public void patrol() 
    {   
        
        if(isPlayerInRange) // Move to catch the player
        {
            inRange();
            //System.out.println("player is in range of the enemy");
            return;
        }
        else
        {
            
        }
        
        if(movingTimer <= 0)
        {
            speed = -speed;
            changedDir = true;
            dirTimer += rand.nextInt(6);
            movingTimer += 2;
        }
        else
        {
            checkIfPlayerInRange();
            if(changedDir && !isPlayerInRange)
            {
                if(dirTimer <= 0)
                {
                    changedDir = false;
                }
                dirTimer -= 0.04;
                return;
            }
            currentHori = speed;
            movingTimer -= 0.04;
            collisions();
            setLocation(getX()+currentHori, getY());
            
            
        }
        
    }
    
    
   // Call this when 
    public void inRange()
    {
        
            turnTowards(
                    ((Link)getWorld().getObjects(Link.class).get(0)).getX(),
                    ((Link)getWorld().getObjects(Link.class).get(0)).getY()
                    );
        
        
        
        System.out.println("facing");
    }
    
    private void checkIfPlayerInRange()
    {
        List<Link> player = getObjectsInRange(visionRange, Link.class);

        if(player.size() > 0)
        {
           isPlayerInRange = true;
            

        }else
        {
            isPlayerInRange = false;
        }


    }
    
    
    public void battleStarted()
    {
        inBattle = true;
    }
    
    
    

    

    

     
}