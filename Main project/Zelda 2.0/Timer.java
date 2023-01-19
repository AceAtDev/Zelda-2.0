/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer  
{
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public static void threadTimer(long sec) throws InterruptedException {
        sec *= 100;
            //try {
         Thread.currentThread().sleep(sec);
        //}
        
=======
=======
>>>>>>> Stashed changes
    public static void threadTimer(long sec) // WARNING: It completely stops the game
    {
        sec *= 100;
            try {
         Thread.currentThread().sleep(sec);
        }
        catch(InterruptedException ie) {
            //ie.printStackTrace(); //only if you want the exception to be printet on the terminal;
        }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
    
    public static void localTimer(long sec)
    {
        boolean isTimering = true;
        sec *= 1000;
        sec += System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while(isTimering)
        {
            if(sec <= currentTime)
            {
                return;
            }
            currentTime = System.currentTimeMillis();
        }
        
    }
    
}
