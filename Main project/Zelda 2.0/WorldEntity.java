import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import greenfoot.Actor;

/**
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldEntity extends Actor
{
    
    
    private int width = 5;
    private int height = 5;
    
    private boolean canBattle = false;
<<<<<<< Updated upstream
    protected boolean isCollWithEnemy = false; // Is the player colliding with an enemy
=======
    private boolean isCollWithEnemy = false; // Is the player colliding with an enemy
>>>>>>> Stashed changes
    
    protected Vector2D movementRaw = new Vector2D(); // Used externally
    protected int currentHori = 0;
    protected int currentVert = 0;
    
    private static Class[] blockers = null;
    private static Class toBattle = null;
    
<<<<<<< Updated upstream
    protected Enemy hitEnemy = null;
    //private RayRange raysUp, raysRight, raysDown, raysLeft;
    private boolean colUp, colRight, colDown, colLeft;
    
    public WorldEntity(int width, int height, Class[] blockers, boolean canBattle, Class toBattle)
=======
    private Actor hitEnemy = null;
    //private RayRange raysUp, raysRight, raysDown, raysLeft;
    private boolean colUp, colRight, colDown, colLeft;
    
    public WorldEntity(int width, int height, Class[] blockers, boolean canBattle, Class[] toBattle)
>>>>>>> Stashed changes
    {
        this.width = width;
        this.height = height;
        this.blockers = blockers;
        this.canBattle = canBattle;
        this.toBattle = toBattle;
    }
    
    public WorldEntity(int width, int height, Class[] blockers)
    {
        this.width = width;
        this.height = height;
        this.blockers = blockers;
    }
    
    
    
    public void collisions()
    {
        
        colUp = runDetectionUp();
        colDown = runDetectionDown();
        colRight = runDetectionRight();
        colLeft = runDetectionLeft();
        
        
<<<<<<< Updated upstream
        hitEnemy = checkForHitEnemy();
=======
        hitingAnEnemy();
>>>>>>> Stashed changes
        
        
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
<<<<<<< Updated upstream
        
=======
        if(canBattle)
        {
            if((checkEnemyDown() || checkEnemyUp() || checkEnemyRight() || checkEnemyLeft()))
            {
                isCollWithEnemy = true;
            }
        }
        else
        {
            isCollWithEnemy = false;
        }
>>>>>>> Stashed changes
    }
    
    
    private boolean runDetectionDown()
    {
        for(Class checker: blockers)
        {
            Actor object = getOneObjectAtOffset(0, getImage().getHeight()/2 + 3, checker);
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
<<<<<<< Updated upstream
=======
            if(object != null)
            {
                return true;
            }
        }
        return false;
    }
    
    
    ///////// For canBattle /////////
    private boolean checkEnemyDown()
    {
        for(Class checker: toBattle)
        {
            Actor object = getOneObjectAtOffset(0, getImage().getHeight()/2 + 3, checker);
            if(object != null)
            {
                hitEnemy = object;
                return true;
            }
        }
        return false;
    }
    private boolean checkEnemyUp()
    {
        for(Class checker: toBattle)
        {
            Actor object = getOneObjectAtOffset(0, -getImage().getHeight()/2 - 2, checker);
            if(object != null)
            {
                hitEnemy = object;
                return true;
            }
        }
        return false;
    }
    private boolean checkEnemyRight()
    {
        for(Class checker: toBattle)
        {
            Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2, 0, checker);
            if(object != null)
            {
                hitEnemy = object;
                return true;
            }
        }
        return false;
    }
    private boolean checkEnemyLeft()
    {
        for(Class checker: toBattle)
        {
            Actor object = getOneObjectAtOffset(-getImage().getWidth()/2-2, 0, checker);
>>>>>>> Stashed changes
            if(object != null)
            {
                hitEnemy = object;
                return true;
            }
        }
        return false;
    }
    
    public Actor getHitEnemy()
    {
        return hitEnemy;
    }
    
    
    ///////// For canBattle /////////
    private Enemy checkForHitEnemy()
    {
        if(!canBattle){return null;} // don't check for the enemy
        
        
        List<Enemy> enemies = getObjectsInRange(20, toBattle);

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
    
    
    public Enemy getHitEnemy()
    {
        return hitEnemy;
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


