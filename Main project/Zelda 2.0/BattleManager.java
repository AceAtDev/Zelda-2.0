import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BattleManager here.
 * 
 * System limit is that it will only work with and only two worlds
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleManager extends Actor
{
    
    
    private static boolean isInBattle = false;
    //Void currentRoom = new ChallenageRoom();
    
    
    public void act()
    {
        
    }
    
    public static void BattleStart()
    {
        if(isInBattle)
        {
            
        }
        
        isInBattle = true;
        Greenfoot.setWorld(new ChallenageRoom());
    }
    public static void BattleEnd()
    {
        isInBattle = false;
        Greenfoot.setWorld(new RandomlyGeneratingDungeon());
    }
}
