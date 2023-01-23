import greenfoot.*;
import java.awt.Rectangle;
import greenfoot.Actor;
import java.util.Random;


public class Link extends WorldEntity
{
    int xmove=0;
    int xmove2=0;
    int ymove=0;
    int ymove2=0;
    int scroll=0;
    int scrollTimer=0;
    GreenfootSound backgroundMusic = new GreenfootSound("Zelda & Chill.mp3");
    
    private static Class[] blockers = new Class[]{Wall.class,Block.class,Lava.class,Water.class};
    private static Class toBattle = Enemy.class;
    
    public Actor enemyTofight = null;
    
    private BattleManager battleManager = null;
    private HealthUI hpUI= null;
    
    private int firstFade = 3;
    private int secondFade = 3; // used to determine the durtion of the fade once the player make 
                                    // make contact with the enemy
    
    
    public Link()
    {
        super(40,40, 1, 99, blockers, true, toBattle);

    }
    
    public void act() // Void Update
    {
        if(currentHp <= 0)
        {
             // destory this object
        }
        //hpDisplayer();
        //System.out.println(firstFade + " " + getCollidingWithEnemy());
        //Methods
        /*
        if(battleManager != null)
        {
              if(battleManager.getInBattle())
                {
                    System.out.println("Update Battle in action");
                    battleManager.battleUpdater();
                }
        }
        */
        
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
            if (scroll==1){xmove=-12; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("right");
               
            }
            if (scroll==2){xmove=12; ymove=0; ((RandomlyGeneratingDungeon)getWorld()).scroll("left");
            
            }
            if (scroll==3){xmove=0; ymove=-12; ((RandomlyGeneratingDungeon)getWorld()).scroll("down");}
            if (scroll==4){xmove=0; ymove=12; ((RandomlyGeneratingDungeon)getWorld()).scroll("up");}
        }else if(scrollTimer>30){
            setLocation(getX()+xmove,getY()+ymove);
            //if (scroll==5){scroll=0;}
            if (scroll==1&&getX()<=30){scroll=0;}
            if (scroll==2&&getX()>=getWorld().getWidth()-30){scroll=0;}
            if (scroll==3&&getY()<=30){scroll=0;}
            if (scroll==4&&getY()>=getWorld().getHeight()-30){scroll=0;}
            //if ()
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
        if(isInBattle){ 
            //System.out.println("Update Battle in action");

            battleManager.battleUpdater();
            if(!canMove)
            {
                return;
            }
        }
        
        
        if (Greenfoot.isKeyDown("a")){currentHori = -speed; }
        if (Greenfoot.isKeyDown("d")){currentHori = speed; }
        if (Greenfoot.isKeyDown("w")){currentVert = -speed;}
        if (Greenfoot.isKeyDown("s")){currentVert = speed;}
        if (! Greenfoot.isKeyDown("a")&&! Greenfoot.isKeyDown("d")){currentHori = 0;}
        if (! Greenfoot.isKeyDown("w")&&! Greenfoot.isKeyDown("s")){currentVert = 0;}
        collisions();
        
        
        
        //System.out.println(getCollidingWithEnemy());

        backgroundMusic.playLoop();
        if(getCollidingWithEnemy()){// stop the player from moving
            currentHori = 0; 
            currentVert = 0;
            
            if(battleManager == null)
            {
                battleManager = new BattleManager(
                                                ((RandomlyGeneratingDungeon)getWorld()).getWidth(),
                                                ((RandomlyGeneratingDungeon)getWorld()).getHeight()/2,
                                                ((RandomlyGeneratingDungeon)getWorld()).getWidth(),
                                                ((RandomlyGeneratingDungeon)getWorld()).getHeight()/2,
                                                this
                                                );
            }
            backgroundMusic.stop();
            // Do effects
            preBattleEffects();
            //if(scroll != 0){return;}
            battleManager.battleStart();// Call out the battle
        }
        
        movementRaw = new Vector2D(currentHori, currentVert);
    }
    
    static String direction="up";
    public void graphics()
    {
        
    }
    
    
    private void preBattleEffects()
    {   
        Greenfoot.playSound("Encounter Full-HQ.wav");
        Greenfoot.delay(20);
        
    }
    
    public void defendingAttack()
    {
        /*
        int currentX = 0;
        int currentY = 0;
        
        getWorld().addObject(new Sowrd(), getX() + 35, getY());
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(mouse!=null){
           currentX = mouse.getX();
           currentY = mouse.getY();
        }
        */
    }
    
    /*
    protected Vector2D pointOnCircle() {

    double rads = Math.toRadians(orbitAngle - 180); // Make 0 point out to the right...
    int fullLength = Math.round((outterRadius));

    // Calculate the outter point of the line
    int xPosy = Math.round((float) (Math.cos(rads) * fullLength));
    int yPosy = Math.round((float) (Math.sin(rads) * fullLength));

    return new Vector2D(xPosy, yPosy);
    }
    */
    
    
    //boolean hpCreated = false;
    public void hpDisplayer()
    {
        hpUI = ((HealthUI)getWorld().getObjects(HealthUI.class).get(0));
        hpUI.positionUpdater();
        //hpUI.updateUi(currentHp);
    }
    
    public FadeOverlay getOverlay()
    {
        return ((FadeOverlay)getWorld().getObjects(FadeOverlay.class).get(0));
    }

    public Enemy getHitEnemy()
    {
        return hitEnemy;
    }
    
    public boolean isInBattle = false;
    boolean canMove = true;
    public void inBattle()
    {
        if(canMove)
        {
            canMove = false;
            isInBattle = true;
        }
    }
    
    public void endedBattle()
    {
        if(!canMove)
        {
            isInBattle = false;
            canMove = true;
        }
    }
    
    
    
}
