import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ChallenageRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChallenageRoom extends World
{
    
    
    /**
     * Constructor for objects of class ChallenageRoom.
     * 
     */
    public ChallenageRoom()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(680, 480, 1, false);
        addObject(new FadeOverlay(),getWidth()/2,getHeight()/2);
        
        
        //clearDungeonRoom();
        background();
    }
    
    public void background()
    {
        setBackground(new GreenfootImage("AquaTile.png"));
    }
    
        public void clearDungeonRoom(){
        Class[] objects = {Block.class,Wall.class}; //List of objects that will be cleared
        int object = 0;
        int i = 0;
        while (object<objects.length){
            List Object = getObjects(objects[object]);
            if (! Object.isEmpty() && (Actor) Object.get(0)!=null){
                while (i<Object.size()){
                    removeObject((Actor) Object.get(i));
                    i++;
                }
            }
            object++;
            i=0;
        }
    }
}
