import java.awt.Rectangle;


/**
 * Write a description of class Bounds here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bounds  
{
    // instance variables - replace the example below with your own
    private Rectangle rect = new Rectangle();

    /**
     * Constructor for objects of class Bounds
     */
    public Bounds(int x, int y, int width, int height)
    {
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }
    
    
    
    public Vector2D center()
    {
        return new Vector2D(rect.width/ 2, rect.height / 2);
    }
    
    
    public int maxX()
    {
        return rect.width;
    }
    
    public int maxY()
    {
        return rect.height;
    }
    
    public int minX()
    {
        return -rect.width;
    }
    
    public int minY()
    {
        return -rect.height;
    }
}
