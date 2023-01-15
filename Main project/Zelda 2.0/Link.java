import greenfoot.*;
import java.awt.Rectangle;
//import java.util.Vector;

public class Link extends WorldEntity
{
    int xmove=0;
    int xmove2=0;
    int ymove=0;
    int ymove2=0;
    int scroll=0;
    int scrollTimer=0;
    
    private static Class[] blockers = {Wall.class,Block.class,Lava.class,Water.class};
    
    public Link()
    {
        super(40,40, blockers);
    }
    
    public void act() // Void Update
    {
        //Methods
        try{
            ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).setLocation(getX(),getY());
        }catch(IndexOutOfBoundsException e){}
        if (scroll==0){
            
            basicMoving();
            
            //System.out.println(currentHori);
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
            if (scroll==1&&getX()<=30){scroll=0;}
            if (scroll==2&&getX()>=getWorld().getWidth()-30){scroll=0;}
            if (scroll==3&&getY()<=30){scroll=0;}
            if (scroll==4&&getY()>=getWorld().getHeight()-30){scroll=0;}
            if (scroll==0){scrollTimer=0; ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeIn();}
        }
    }
    private int speed = 3;
    Bounds b = new Bounds();
    public void basicMoving()
    {
        if (scroll!=0)return;
         //Rate of cells that will be traveled; Player speed
        //Change movement
        //System.out.println("X: " + xmove + ", Y: " + ymove);
        
        if (Greenfoot.isKeyDown("a")){currentHori = -speed; setRotation(270);}
        if (Greenfoot.isKeyDown("d")){currentHori = speed; setRotation(90);}
        if (Greenfoot.isKeyDown("w")){currentVert = -speed; setRotation(0);}
        if (Greenfoot.isKeyDown("s")){currentVert = speed; setRotation(180);}
        if (! Greenfoot.isKeyDown("a")&&! Greenfoot.isKeyDown("d")){currentHori = 0;}
        if (! Greenfoot.isKeyDown("w")&&! Greenfoot.isKeyDown("s")){currentVert = 0;}
        collisions();
        
        
        b = new Bounds(getX(), getY(), 40,40);
        
        int t = 100;
        getWorld().getBackground().drawLine(b.minX() + t, b.minY() + t, b.maxX() + t, b.minY() + t);
        getWorld().getBackground().drawLine(b.minX() + t, b.maxY() + t, b.maxX() + t, b.maxY() + t);
        getWorld().getBackground().drawLine(b.minX() + t, b.minY() + t, b.minX() + t, b.maxY() + t);
        getWorld().getBackground().drawLine(b.maxX() + t, b.minY() + t, b.maxX() + t, b.maxY() + t);
        
        
        movementRaw = new Vector2D(currentHori, currentVert);
        System.out.println(movementRaw.x() + "," + movementRaw.y());
    }
    
    static String direction="up";
    public void graphics()
    {
        
    }
    Class[] objects = {Wall.class,Block.class,Lava.class,Water.class, Enemy.class};
    int collisionAmount=0;
    //int horizontalCollisionOffset = 2;
    
    //public Rectangle solidArea = new Rectangle(5,10,30,30);
    public void collisionDetection()
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
