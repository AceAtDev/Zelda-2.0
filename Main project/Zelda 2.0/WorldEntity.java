import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldEntity extends Actor
{
    
    
    private int width = 5;
    private int height = 5;
    
    protected Vector2D movementRaw = new Vector2D(); // Used externally
    protected double currentHori = 0;
    protected double currentVert = 0;
    
    private static Class[] blockers = null;
    
    private RayRange raysUp, raysRight, raysDown, raysLeft;
    private boolean colUp, colRight, colDown, colLeft;
    
    public WorldEntity(int width, int height, Class[] blockers)
    {
        this.width = width;
        this.height = height;
        this.blockers = blockers;
    }
    
    
    
    public void collisions()
    {
        calculateRayRanged();
        
        colUp = runDetectionUp();
        colDown = runDetectionDown();
        colRight = runDetectionRight();
        colLeft = runDetectionLeft();
        
        
        // Horizontal movement
        if(currentHori > 0 && colRight || currentHori < 0 && colLeft)
        {
            currentHori = 0; 
        }
        // Vertical movement
        // FOR SOME REASON THE UP VALUE IS NEGTIVE AND DOWN IS POSITIVE, 
        // I ASSUME THIS WORLD IS RUNNING ON A 2D ARRAY WHCIH EXPLINES THIS NON-SANCE
        if(currentVert < 0 && colUp || currentVert > 0 && colDown)
        { 
            currentVert = 0; 
        }
        
        
        
        
    }
    
    private void calculateRayRanged()
    {
        Bounds b = new Bounds(getX(), getY(), width, height);
        
        raysUp = new RayRange(b.minX() , b.minY() - 5, b.maxX(), b.minY() - 5, new Vector2D(0,1));
        raysDown = new RayRange(b.minX(), b.maxY(), b.maxX(), b.maxY(), new Vector2D(0,-1));
        raysLeft = new RayRange(b.minX(), b.minY(), b.minX(), b.maxY(), new Vector2D(-1,0));
        raysRight = new RayRange(b.maxX(), b.minY(), b.maxX(), b.maxY(), new Vector2D(1,0));
    }
    
    //Class[] objects = {Wall.class,Block.class,Lava.class,Water.class};
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
            Actor object = getOneObjectAtOffset(0, getImage().getHeight()/2 - 4, checker);
            if(object != null)
            {
                return true;
            }
        }
        return false;
    }
    private boolean runDetectionRight()
    {
        for(Class c: blockers)
        {
            List<Actor> bs = getIntersectingObjects(c);
            for(Actor b: bs)
            {
                if(b.getX() > this.getX())
                    return true;
            }
        }
        return false;
    }
    private boolean runDetectionLeft()
    {
        for(Class c: blockers)
        {
            List<Actor> bs = getIntersectingObjects(c);
            for(Actor b: bs)
            {
                if(b.getX() < this.getX())
                    return true;
            }
        }
        return false;
    }
    
    //Getters//
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
    
    public Vector2D setMovement(double x, double y)
    {
        return new Vector2D(x,y);
    }
    
    
    
}


