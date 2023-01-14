import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    
    
    private int width = 5;
    private int height = 5;
    
    protected Vector2D movementRaw = new Vector2D(); // Used externally
    protected double currentHori = 0;
    protected double currentVert = 0;
    
    private RayRange raysUp, raysRight, raysDown, raysLeft;
    private boolean colUp, colRight, colDown, colLeft;
    
    public Entity(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    
    public void collisions()
    {
        calculateRayRanged();
        
        colUp = runDetection(raysUp);
        colDown = runDetection(raysDown);
        colRight = runDetection(raysRight);
        colLeft = runDetection(raysLeft);
        
        
        // Horizontal movement
        if(currentHori > 0 && colRight || currentHori < 0 && colLeft){ currentHori = 0; 
        System.out.println("Hori");}
        // Vertical movement
        if(currentVert > 0 && colUp || currentVert < 0 && colDown) { currentVert = 0; 
        System.out.println("Vert");}
        
        
        
        // DEBUG///////////////////////
        System.out.println("Right: " + colRight);
        System.out.println("Left: " + colLeft);
        System.out.println("Up: " + colUp);
        System.out.println("Down: " + colDown);
        
        
        Bounds b = new Bounds(getX(), getY(), width, height);
        
        int t = 100;
        getWorld().getBackground().drawLine(b.minX() + t, b.minY() + t, b.maxX() + t, b.minY() + t);
        getWorld().getBackground().drawLine(b.minX() + t, b.maxY() + t, b.maxX() + t, b.maxY() + t);
        getWorld().getBackground().drawLine(b.minX() + t, b.minY() + t, b.minX() + t, b.maxY() + t);
        getWorld().getBackground().drawLine(b.maxX() + t, b.minY() + t, b.maxX() + t, b.maxY() + t);
        
         MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != (null)){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            GreenfootImage drawedImage = new GreenfootImage("Block.png");
            getWorld().getBackground().drawImage(drawedImage, 100, 100);
        }  
        
        
        
    }
    
    private void calculateRayRanged()
    {
        Bounds b = new Bounds(getX(), getY(), width, height);
        
        raysDown = new RayRange(b.minX() , b.minY(), b.maxX(), b.minY(), new Vector2D(0,-1));
        raysUp = new RayRange(b.minX(), b.maxY(), b.maxX(), b.maxY(), new Vector2D(0,1));
        raysLeft = new RayRange(b.minX(), b.minY(), b.minX(), b.maxY(), new Vector2D(-1,0));
        raysRight = new RayRange(b.maxX(), b.minY(), b.maxX(), b.maxY(), new Vector2D(1,0));
    }
    
    Class[] objects = {Wall.class,Block.class,Lava.class,Water.class, Entity.class};
    private boolean runDetection(RayRange range)
    {
        for(int i = 0; i < objects.length; i++)
        {
            Actor object = getOneIntersectingObject(objects[i]);
            if(object != null)
            {
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


