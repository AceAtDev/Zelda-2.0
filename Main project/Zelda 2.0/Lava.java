import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lava here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lava extends Actor
{ 
       
   
    public void act() 
    {
       Actor block = getOneObjectAtOffset(0,0,Block.class); 
       if(block != null){
           getWorld().removeObject(this);
           return;
       }
       
    }    
}
