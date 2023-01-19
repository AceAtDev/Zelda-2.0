import greenfoot.*;
import java.awt.Rectangle;
import greenfoot.Actor;

//import java.util.Vector;

//import java.util.Vector;

public class Link extends WorldEntity
{
    int xmove=0;
    int xmove2=0;
    int ymove=0;
    int ymove2=0;
    int scroll=0;
    int scrollTimer=0;
    
    
    private static Class[] blockers = new Class[]{Wall.class,Block.class,Lava.class,Water.class};
    private static Class[] toBattle = new Class[]{Enemy.class};
    
    public Actor enemyTofight = null;
    
    
    public Link()
    {
        super(40,40, blockers, true, toBattle);
    }
    
    public void act() // Void Update
    {
        
        //Methods
        try{
            ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).setLocation(getX(),getY());
        }catch(IndexOutOfBoundsException e){}
        if (scroll==0){
            basicMoving();
            
            //System.out.println();
            
            
            
            graphics();
            //collisionDetection();
            
            if (getX()>=getWorld().getWidth()-1){scroll=1;}
            if (getX()<=0){scroll=2;}
            if (getY()>=getWorld().getHeight()-1){scroll=3;}
            if (getY()<=0){scroll=4;}
        }
        if (scroll==0){setLocation(getX()+((int)movementRaw.x()),getY()+((int)movementRaw.y()));}
        else{scroll();}
        
        
        //System.out.println("X: " + getX());
        //System.out.println("Y: " + getY());
    }
    
    
    public void scroll(){ //The Camera\Room transformer
        if (scrollTimer==0){((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeOut();}
        scrollTimer++;
        if(scrollTimer==15){
            if (scroll==1){xmove=-12; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("right");}
            if (scroll==2){xmove=12; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("left");}
            if (scroll==3){xmove=0; ymove=-12; ((RandomlyGeneratingDungeon)getWorld()).scroll("down");}
            if (scroll==4){xmove=0; ymove=12; ((RandomlyGeneratingDungeon)getWorld()).scroll("up");}
        }else if(scrollTimer>30){
            setLocation(getX()+xmove,getY()+ymove);
            //if (scroll==5){scroll=0;}
            if (scroll==1&&getX()<=30){scroll=0;}
            if (scroll==2&&getX()>=getWorld().getWidth()-30){scroll=0;}
            if (scroll==3&&getY()<=30){scroll=0;}
            if (scroll==4&&getY()>=getWorld().getHeight()-30){scroll=0;}
            if (scroll==0){
                scrollTimer=0;
                ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeIn();
            }
        }
    }
    private int speed = 3;
    //Bounds b = new Bounds();
    public void basicMoving()
    {
        if (scroll!=0)return;
         //Rate of cells that will be traveled; Player speed
        //Change movement
        //System.out.println("X: " + xmove + ", Y: " + ymove);
        if(!canMove){ return; }
        
        
        if (Greenfoot.isKeyDown("a")){currentHori = -speed; setRotation(270);}
        if (Greenfoot.isKeyDown("d")){currentHori = speed; setRotation(90);}
        if (Greenfoot.isKeyDown("w")){currentVert = -speed; setRotation(0);}
        if (Greenfoot.isKeyDown("s")){currentVert = speed; setRotation(180);}
        if (! Greenfoot.isKeyDown("a")&&! Greenfoot.isKeyDown("d")){currentHori = 0;}
        if (! Greenfoot.isKeyDown("w")&&! Greenfoot.isKeyDown("s")){currentVert = 0;}
        collisions();
        
        
        
        System.out.println(getCollidingWithEnemy());

        
        if(getCollidingWithEnemy()){// stop the player from moving
            currentHori = 0; 
            currentVert = 0;
            ((BattleManager)getWorld().getObjects(BattleManager.class).get(0)).battleStart();
        }
        
        movementRaw = new Vector2D(currentHori, currentVert);
    }
    
    static String direction="up";
    public void graphics()
    {
        
    }
    
    public Enemy getHitEnemy()
    {
        return getHitEnemy();
    }
    
    
    boolean canMove = true;
    public void inBattle()
    {
        if(canMove)
        {
            canMove = false;
        }
    }
    
    public void endedBattle()
    {
        if(!canMove)
        {
            canMove = true;
        }
    }
    
    
    
    
}
