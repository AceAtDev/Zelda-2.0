import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import greenfoot.Actor;

/**
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldEntity extends Actor
{
    
    protected String name = "";
    private int hp = 3;
    protected int currentHp;
    protected int damage = 1;
    
    private int width = 5;
    private int height = 5;
    
    private boolean isAlive = true;
    private boolean canBattle = false;
    protected boolean isCollWithEnemy = false; // Is the player colliding with an enemy
    
    protected Vector2D movementRaw = new Vector2D(); // Used externally
    protected int currentHori = 0;
    protected int currentVert = 0;
    
    private static Class[] blockers = null;
    private static Class toBattle = null;
    
    protected Enemy hitEnemy = null;
    //private RayRange raysUp, raysRight, raysDown, raysLeft;
    private boolean colUp, colRight, colDown, colLeft;
    
    public WorldEntity(int width, int height,
                       int hp, int damage, Class[] blockers, boolean canBattle, Class toBattle)
    {
        this.width = width;
        this.height = height;
        this.blockers = blockers;
        this.canBattle = canBattle;
        this.toBattle = toBattle;
        this.hp = hp;
        currentHp = hp;
    }
    
    public WorldEntity(int width, int height,
                        int hp, int damage, Class[] blockers)
    {
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.currentHp = hp;
        this.damage = damage;
        this.blockers = blockers;
    }
    
    
    
    public void collisions()
    {
        
        if(this == null){return;}
        
        colUp = runDetectionUp();
        colDown = runDetectionDown();
        colRight = runDetectionRight();
        colLeft = runDetectionLeft();
        
        
        hitEnemy = checkForHitEnemy();
        checkDie();
        
        
        // Horizontal movement
        if(currentHori > 0 && colRight || currentHori < 0 && colLeft)
        {
            currentHori = 0; 
        }
        // Vertical movement
        // FOR SOME REASON THE UP VALUE IS NEGTIVE AND DOWN IS POSITIVE, 
        // I ASSUME THIS WORLD IS RUNNING ON A 2D ARRAY WHCIH EXPLINES A LOT >:(
        if(currentVert < 0 && colUp || currentVert > 0 && colDown)
        { 
            currentVert = 0; 
        }
        
        
        
        
    }
    
    private void hitingAnEnemy()
    {
        
    }
    
    
    private boolean runDetectionDown()
    {
        for(Class checker: blockers)
        {
            Actor object = getOneObjectAtOffset(0, getImage().getHeight()/2 + 9, checker);
            if(object != null)
            {
                return true;
            }
        }
        return false;
    }
    private boolean runDetectionUp()
    {
        for(Class checker: blockers)
        {
            Actor object = getOneObjectAtOffset(0, -getImage().getHeight()/2 - 2, checker);
            if(object != null)
            {
                return true;
            }
        }
        return false;
    }
    private boolean runDetectionRight()
    {
        for(Class checker: blockers)
        {
            Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2, 0, checker);
            if(object != null)
            {
                return true;
            }
        }
        return false;
    }
    private boolean runDetectionLeft()
    {
        for(Class checker: blockers)
        {
            Actor object = getOneObjectAtOffset(-getImage().getWidth()/2-2, 0, checker);
            if(object != null)
            {
                return true;
            }
        }
        return false;
    }
    
    
    ///////// For canBattle /////////
    private Enemy checkForHitEnemy()
    {
        if(!canBattle){return null;} // don't check for the enemy
        
        
        List<Enemy> enemies = getObjectsInRange(40, toBattle);

        if(enemies.size() > 0)
        {
            isCollWithEnemy = isTouching(Enemy.class);
            //System.out.println(enemies.get(0));
            return enemies.get(0);

        }else
        {
            isCollWithEnemy = false;
        }


        return null;
    }
    
    private void checkDie()
    {
        if(!canBattle){return;} // don't check for the enemy
        
        
        List<projectile> enemies = getObjectsInRange(13, projectile.class);

        if(enemies.size() > 0)
        {
            //isCollWithEnemy = isTouching(Enemy.class);
            //System.out.println(enemies.get(0));
            currentHp = 0;

        }else
        {
            
        }


    }
    
    
    public Enemy getHitEnemy()
    {
        return hitEnemy;
    }
    
    
    public int getCurrentHp()
    {
        return currentHp;
    }
    
    
    public int attack()
    {
        return damage;
    }
    
    public void takeDamage(int damage)
    {
         Greenfoot.playSound("Soul Damaged-HQ.wav");
         currentHp -= damage;
         if(currentHp <= 0)
         {
             isAlive = false;
         }
         
         
    }
    
    public boolean getIsAlive()
    {
        return isAlive;
    }
    
    
    //Getters//
    
    public boolean getCollidingWithEnemy()
    {
        return isCollWithEnemy;
    }
    
    public int xPos()
    {
        return getX();
    }
    
    public int yPos()
    {
        return getY();
    }
    
    public Vector2D movement()
    {
        return movementRaw;
    }
    
    public Vector2D setMovement(int x, int y)
    {
        return new Vector2D(x,y);
    }
    
    
    
}


