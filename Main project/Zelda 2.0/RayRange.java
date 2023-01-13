/**
 * Write a description of class RayRange here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RayRange  
{
    // instance variables - replace the example below with your own
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    
    private final Vector2D Dir;
    public final Vector2D start;
    public final Vector2D end;
    
    public RayRange(double x1, double y1, double x2, double y2, Vector2D Dir)
    {
        this.start = new Vector2D(x1,y1);
        this.end = new Vector2D(x2,y2);
        this.Dir = Dir;
    }
    
    

}
