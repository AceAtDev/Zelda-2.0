/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer  
{
    public static void threadTimer(long sec) throws InterruptedException {
        sec *= 100;
            //try {
         Thread.currentThread().sleep(sec);
        //}
        
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
