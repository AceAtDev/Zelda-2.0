/**
 * Write a description of class MathTools here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector2D  
{
    // instance variables - replace the example below with your own

    private int x = 0;
    private int y = 0;
    
    public Vector2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Vector2D()
    {
        this.x = x;
        this.y = y;
    }
    
    
    public int x()// getter; returns x
    {
        return x;
    }
    
    public int y() // getter; returns y
    {
        return y;
    }
    
    public static double Lerp(double start_value, double end_value, double time)
    {
    return (start_value + (end_value - start_value) * time);
    }
}
