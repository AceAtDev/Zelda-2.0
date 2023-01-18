/**
 * Write a description of class RayRange here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RayRange  
{
    // instance variables - replace the example below with your own
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    private final Vector2D Dir;
    public final Vector2D start;
    public final Vector2D end;
    
    public RayRange(int x1, int y1, int x2, int y2, Vector2D Dir)
    {
        this.start = new Vector2D(x1,y1);
        this.end = new Vector2D(x2,y2);
        this.Dir = Dir;
    }
    
    

}
