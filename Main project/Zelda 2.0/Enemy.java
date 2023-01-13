import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The idea of this is that the enemy will move around the wrold to try and find the player.
 * Once the player in range, the enemy will start chessing him.
 * When the enemy catchs the player, the enemy and the player to get transformed to another room to fight with
 * an RPG style
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    protected String name = "";
    protected int hp = 3;
    protected int damage = 1;
    //public int Range = 3;
    protected int speed = 3;
    
    private int xmove = 0;
    private int ymove = 0;
    
    
    public Enemy(int hp, int damage, int speed){
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
    }
    
    public Enemy(String name ,int hp, int damage, int speed){ 
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
    }
    
    public void act()
    {
        dectectCollision();
    }
    
    
    // Looking for the player
    public void patrol() 
    {
        // move around to look for the player and fight him
        
    }
    
    // Call this when 
    public void inRange()
    {
    
    }
    
    // Call this to TP the player to the fight room
    public static void challenagePlayer()
    {
        
    }
    
    Class[] objects = {Wall.class,Block.class,Lava.class,Water.class, Link.class};
    int collisionAmount=0;
    
    public void dectectCollision()
    {
        while (collisionAmount<objects.length){
            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+3,objects[collisionAmount]);
                if (object!=null&&ymove>0)
                {
                    i=-getImage().getWidth()/2+2; 
                    ymove -= ymove; 
                    //setLocation(getX(),
                                //object.getY()-object.getImage().getHeight()/2-getImage().getHeight()/2);
                }
            }
            //System.out.println("Down: " + 20);

            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,objects[collisionAmount]);
                
                if (object!=null&&ymove<0)
                {
                    i=-getImage().getWidth()/2+2; 
                    ymove -= ymove; 
                    //setLocation(getX(), 
                                //object.getY()+object.getImage().getHeight()/2+getImage().getHeight()/2);
                }
            }
            //System.out.println("Up: " + 20);
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(0-getImage().getWidth()/2-3, i,objects[collisionAmount]);
                if (object!=null&&xmove<0)
                {
                    i=-getImage().getHeight()/2+2; 
                    xmove -= xmove; // -3 - -3 ; don't move 
                    //setLocation(object.getX()+object.getImage().getWidth()/2+getImage().getWidth()/2, 
                                //getY());
                    
                }
            }
            //System.out.println("Left: " + -getImage().getWidth()/horizontalCollisionOffset);

            //Right check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-3; i+=4){
                Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2, i,objects[collisionAmount]);
                if (object!=null&&xmove>0)
                {
                    i=-getImage().getHeight()/2+2;
                    xmove -= xmove; 
                    //setLocation(object.getX()-object.getImage().getWidth()/2-getImage().getWidth()/2,
                                //getY());
                }
            }
            //System.out.println("Right: " + getImage().getWidth()/horizontalCollisionOffset);

            collisionAmount++;
        }
            collisionAmount=0;
    }
    }

