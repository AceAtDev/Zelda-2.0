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
    private boolean canDoubleSwarm = false;
    
    
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

    public Enemy(int hp, int damage, int speed, double rate, boolean canDoubleSwarm) // The less the rate the faster
    {
        super(10,10, hp, damage, blocks);
        this.canDoubleSwarm = canDoubleSwarm;
        this.delayer = rate;
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
    private double chaseDelay = 3;
    private boolean playedSound = false;
    public void patrol() 
    {   
        
        if(isPlayerInRange && chaseDelay <= 0) // Move to catch the player
        {
            
                inRange();
                //System.out.println("player is in range of the enemy");
                return;
            
        }
        else if(isPlayerInRange)
        {
            if(!playedSound)
            {
                Greenfoot.playSound("epic-sword-clang-2.wav");
                playedSound = true;
            }
            
            chaseDelay -= 0.06;
            preChessing();
            return;
        }
        
        if(movingTimer <= 0)
        {
            speed = -speed;
            
            setRotation(180 + getRotation());
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
    
    
    public void preChessing()
    {
        turnTowards(
                    ((Link)getWorld().getObjects(Link.class).get(0)).getX(),
                    ((Link)getWorld().getObjects(Link.class).get(0)).getY()
                    );
    }
    
    
    int followingSpeed = 5;
    public void inRange()
    {
        turnTowards(
                    ((Link)getWorld().getObjects(Link.class).get(0)).getX(),
                    ((Link)getWorld().getObjects(Link.class).get(0)).getY()
                    ); // look at the player
                    
        int deltaX = ((Link)getWorld().getObjects(Link.class).get(0)).getX() - getX();
        int deltaY = ((Link)getWorld().getObjects(Link.class).get(0)).getY() - getY();
        int angle = (int) Math.atan2( deltaY, deltaX );
        
        
        int currentX = (int) (followingSpeed * Math.cos(angle));
        int currentY = (int) (followingSpeed * Math.sin(angle));
        
        setLocation(getX()+ currentX, getY()+ currentY);
        
        
        
        
        //System.out.println("facing");
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
    
    private int xOffset = 40;
    private int yOffset = 40;
    private int startingTopBlock;
    public boolean isTrapped = false;
    private Block[] cage = new Block[16];
    private int index = 0;
    //private
    public void enemyCage(Link player)
    {
        //getWorld().addObject(new Block(false, 0,0), player.getX() + xOffset, player.getY());
        for(int i = 0; i < 16; i++)
        {
            cage[i] = new Block(false,0,1);
        }
        startingTopBlock = player.getY() - 120;
        
        getWorld().addObject(cage[0], player.getX(), startingTopBlock);
        Greenfoot.playSound("bumpelsnake__bump3.wav");
        Greenfoot.delay(10);
        for(int i = 1; i < 15; i++)
        {
            
            
            if(i < 8)
            {
            getWorld().addObject(cage[i], player.getX() + xOffset, startingTopBlock + (yOffset * (i-1)));
            }
            else{
            getWorld().addObject(cage[(i)], player.getX() - xOffset, startingTopBlock + (yOffset * index));
            index++;
            }
            //index++;
            Greenfoot.playSound("bumpelsnake__bump3.wav");
            Greenfoot.delay(10);
        }
        index = 0;
        getWorld().addObject(cage[15], player.getX(), startingTopBlock + (yOffset * 6));
        isTrapped = true;
        Greenfoot.playSound("bumpelsnake__bump3.wav");

    }
    
    public boolean finishedAttacking = false;
    private double delayer = 0.3;
    private int amountOfBees = 13;
    private double finishingDelay = 0.9;
    
    
    public void EnemyAttack(Link player)
    {
        if(amountOfBees <= 0)
        {
            
            if(finishingDelay <= 0)
            {
                delayer = 0.3;
                amountOfBees = 13;
                finishingDelay = 0.9;
                finishedAttacking = true;
                //System.out.println("finished");
            }else
            {
                finishingDelay -= 0.01;
            }
             
        }
        else if(delayer <= 0 && amountOfBees > 0)
        {
            delayer += 2;
            amountOfBees -= 1;
            getWorld().addObject(new projectile(player.getX(), player.getY()), (getX() - 100)+ rand.nextInt(300), (getY() + 150) - rand.nextInt(340));
            if(canDoubleSwarm)
            {
                getWorld().addObject(new projectile(player.getX(), player.getY()), (getX() - 100)+ rand.nextInt(300), (getY() + 150) - rand.nextInt(340));
            }
        }else
        {
            delayer -= 0.07;
            //System.out.println(delayer);    
        }
        
    
    }
    
    public void removeCage()
    {
        for(Block block : cage)
        {
            getWorld().removeObject(block);
        }
    }
    
    public void resetBooleans()
    {
        finishedAttacking = false;
        isTrapped = false;
    }
    
    
    
    
    public void battleStarted()
    {
        inBattle = true;
    }
    
    
    

    

    

     
}