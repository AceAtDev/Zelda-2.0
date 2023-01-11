import greenfoot.*;

public class Link extends Actor
{
    int xmove=0;
    int xmove2=0;
    int ymove=0;
    int ymove2=0;
    int scroll=0;
    int scrollTimer=0;
    public void act() // Void Update
    {
        //Methods
        try{
            ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).setLocation(getX(),getY());
        }catch(IndexOutOfBoundsException e){}
        if (scroll==0){
            basicMoving();
            //graphics();
            collisionDetection();
            if (getX()>=getWorld().getWidth()-1){scroll=1;}
            if (getX()<=0){scroll=2;}
            if (getY()>=getWorld().getHeight()-1){scroll=3;}
            if (getY()<=0){scroll=4;}
        }
        if (scroll==0){setLocation(getX()+xmove+xmove2,getY()+ymove+ymove2);}else{scroll();}
    }
    public void scroll(){ //The Camera\Room transformer
        if (scrollTimer==0){((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeOut();}
        scrollTimer++;
        if(scrollTimer==15){
            if (scroll==1){xmove=-9; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("right");}
            if (scroll==2){xmove=9; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("left");}
            if (scroll==3){xmove=0; ymove=-9; ((RandomlyGeneratingDungeon)getWorld()).scroll("down");}
            if (scroll==4){xmove=0; ymove=9; ((RandomlyGeneratingDungeon)getWorld()).scroll("up");}
        }else if(scrollTimer>30){
            setLocation(getX()+xmove,getY()+ymove);
            if (scroll==1&&getX()<=30){scroll=0;}
            if (scroll==2&&getX()>=getWorld().getWidth()-30){scroll=0;}
            if (scroll==3&&getY()<=30){scroll=0;}
            if (scroll==4&&getY()>=getWorld().getHeight()-30){scroll=0;}
            if (scroll==0){scrollTimer=0; ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0)).fadeIn();}
        }
    }
    int Speed = 3; 
    public void basicMoving()
    {
        if (scroll!=0)return;
         //Rate of cells that will be traveled; Player speed
        //Change movement
        //System.out.println("X: " + xmove + ", Y: " + ymove);
        
        
        if (Greenfoot.isKeyDown("a")){xmove=-Speed; setRotation(270);}
        if (Greenfoot.isKeyDown("d")){xmove=Speed; setRotation(90);}
        if (Greenfoot.isKeyDown("w")){ymove=-Speed; setRotation(0);}
        if (Greenfoot.isKeyDown("s")){ymove=Speed; setRotation(180);}
        if (! Greenfoot.isKeyDown("a")&&! Greenfoot.isKeyDown("d")){xmove=0;}
        if (! Greenfoot.isKeyDown("w")&&! Greenfoot.isKeyDown("s")){ymove=0;}
        rotationHundler();
    }
    
    private void rotationHundler(){
        
    }
    
    static String direction="up";
    public void graphics()
    {
        
    }
    Class[] objects = {Wall.class,Block.class,Lava.class,Water.class};
    int collisionAmount=0;
    int upperOffset = 4; // The smaller the value the higher the offset.
    int horizontalOffset = 4; //
    public void collisionDetection()
    {
        while (collisionAmount<objects.length){
            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+3,objects[collisionAmount]);
                if (object!=null&&ymove>0)
                {
                    i=-getImage().getWidth()/2+2; 
                    ymove=0; 
                    setLocation(getX(),
                                object.getY()-object.getImage().getHeight()/2-getImage().getHeight()/2);
                }
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-3,objects[collisionAmount]);
                if (object!=null&&ymove<0)
                {
                    i=-getImage().getWidth()/2+2; 
                    ymove=0; 
                    setLocation(getX(), 
                                object.getY()+object.getImage().getHeight()/2+getImage().getHeight()/2);
                }
            }
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i+=4){
                Actor object = getOneObjectAtOffset(0-getImage().getWidth()/2-3, i,objects[collisionAmount]);
                if (object!=null&&xmove<0)
                {
                    i=-getImage().getHeight()/2+2; 
                    xmove=0; 
                    setLocation(object.getX()+object.getImage().getWidth()/2+getImage().getWidth()/2, 
                                getY());
                }
            }
            //Right check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-3; i+=4){
                Actor object = getOneObjectAtOffset(getImage().getWidth()/2+2, i,objects[collisionAmount]);
                if (object!=null&&xmove>0)
                {
                    i=-getImage().getHeight()/2+2;
                    xmove=0; 
                    setLocation(object.getX()-object.getImage().getWidth()/2-getImage().getWidth()/2,
                                getY());
                }
            }
            collisionAmount++;
        }
            collisionAmount=0;
    }
}
