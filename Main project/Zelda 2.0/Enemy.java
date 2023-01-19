import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.awt.Rectangle;

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
    protected String name = "";
    protected int hp = 3;
    protected int damage = 1;
    protected int speed = 1;
    
    
    
    
    static Class[] blocks = new Class[]{Wall.class,Block.class,Lava.class,Water.class};
    static Class[] opponent = new Class[]{Link.class};

    public Enemy(int hp, int damage, int speed)
    {
        super(10,10, blocks, true, opponent);
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
    }
    
    public Enemy(String name ,int hp, int damage, int speed){ 
        super(10,10, blocks);
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
    }
    
    public void act()
    {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        
        movementUpdater();
=======
        //collisionDetection();
>>>>>>> Stashed changes
=======
        //collisionDetection();
>>>>>>> Stashed changes
        collisions();
        patrol();
    }
    
    
    
    boolean ispatrolling = true;
    boolean isPlayerInRange = false;
    
    //boolean isColliding = false;
    
    Random rand = new Random();
    private double movingTimer = 0;
    // Looking for the player
    public void patrol() 
    {   
        if(isPlayerInRange) // Move to catch the player
        {
            inRange();
            System.out.println("player is in range of the enemy");
            return;
        }
        
        if(movingTimer <= 0)
        {
            speed = -speed;
            movingTimer += 2/*rand.nextInt(3)*/;
        }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        else
        {
            movingTimer -= 0.04;
=======
        if(getCollidingWithEnemy())
        {
=======
        if(getCollidingWithEnemy())
        {
>>>>>>> Stashed changes
            
        }
        else
        {
            movingTimer -= 0.04;
<<<<<<< Updated upstream
=======
            
            setLocation(getX()+speed, getY());
            
>>>>>>> Stashed changes
            
            setLocation(getX()+speed, getY());
            
>>>>>>> Stashed changes
            
            setLocation(getX()+speed, getY());
        }
        
    }
    
    
    double losingRange = 5; // the limit range that the enemy can follow the player while in range
    // Call this when 
    public void inRange()
    {
        
<<<<<<< Updated upstream
<<<<<<< Updated upstream
         
    }
    
    
    private void movementUpdater()
    {
        currentHori = speed;
        
        collisions();
=======
=======
>>>>>>> Stashed changes
        if(caughtPlayer) // Enemy knows if caught player by colliding with the player; check enemy collisions!
        {
            System.out.println("Enemy caught the player");
        }
>>>>>>> Stashed changes
    }
    
    // Call this to TP the player to the fight room
    public static void challenagePlayer()
    {
        
    }
    
<<<<<<< Updated upstream
=======
    Class[] objects = {Wall.class,Block.class,Lava.class,Water.class};
    int collisionAmount=0;    
<<<<<<< Updated upstream
    
    
>>>>>>> Stashed changes
=======
    
    
>>>>>>> Stashed changes
     
}