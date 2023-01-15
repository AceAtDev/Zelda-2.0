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
    
    private int xmove = 1;
    private int ymove = 1;
    
    
    static Class[] blocks = new Class[]{Wall.class,Block.class,Lava.class,Water.class};
    public Enemy(int hp, int damage, int speed)
    {
        super(10,10, blocks);
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
        collisionDetection();
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
        if(isColliding)
        {
            speed = -speed;
            return;
        }
        else
        {
            movingTimer -= 0.04;
            if(!isColliding)
            {
                move((xmove*speed)/*+ymove*/);
            }
            
        }
        
    }
    
    
    boolean caughtPlayer = false;
    double losingRange = 5; // the limit range that the enemy can follow the player while in range
    // Call this when 
    public void inRange()
    {
        
        
        if(caughtPlayer) // Enemy knows if caught player by colliding with the player; check enemy collisions!
        {
            System.out.println("Enemy caught the player");
        }
    }
    
    // Call this to TP the player to the fight room
    public static void challenagePlayer()
    {
        
    }
    
    Class[] objects = {Wall.class,Block.class,Lava.class,Water.class,WorldEntity.class};
    int collisionAmount=0;
    
    boolean isColliding = false;
    Rectangle hitBox = new Rectangle();
    public void collisionDetection()
    {
        while (collisionAmount<objects.length){
            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+3,objects[collisionAmount]);
                if (object!=null&&ymove>0)
                {
                    i=-getImage().getWidth()/2+2; 
                    //ymove -= ymove; 
                    isColliding = true;
                    //setLocation(getX(),
                                //object.getY()-object.getImage().getHeight()/2-getImage().getHeight()/2);
                }
            }
            //System.out.println("Down: " + 20);

            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,objects[collisionAmount]);
                
                if (object!=null&&ymove<0)
                {
                    i=-getImage().getWidth()/2+2; 
                    //ymove = -ymove; 
                    isColliding = true;
                    //setLocation(getX(), 
                                //object.getY()+object.getImage().getHeight()/2+getImage().getHeight()/2);
                }
            }
            //System.out.println("Up: " + 20);
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(0-getImage().getWidth()/2-3, i,objects[collisionAmount]);
                if (object!=null&&xmove<0)
                {
                    i=-getImage().getHeight()/2+2; 
                    //xmove = -xmove; // -3 - -3 ; don't move
                    isColliding = true;
                    //setLocation(object.getX()+object.getImage().getWidth()/2+getImage().getWidth()/2, 
                                //getY());
                    
                }
            }
            //System.out.println("Left: " + -getImage().getWidth()/horizontalCollisionOffset);

            //Right check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-3; i+=4){
                Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2, i,objects[collisionAmount]);
                if (object!=null&&xmove>0)
                {
                    i=-getImage().getHeight()/2+2;
                    //xmove = -xmove; 
                    isColliding = true;
                    //setLocation(object.getX()-object.getImage().getWidth()/2-getImage().getWidth()/2,
                                //getY());
                }
            }
            //System.out.println("Right: " + getImage().getWidth()/horizontalCollisionOffset);

            collisionAmount++;
        }
            collisionAmount=0;
            
            
    }
     
}