import greenfoot.*;

public class Block extends Actor
{
    int xmove=0;
    int ymove=0;
    int moves=0;
    int event=0;//Determinds which event activate apon moving.
    int keypos=-1;
    /**
    -1 means the block is infinitely movable
    0 means no event is triggered
    1 means a key is dropped from the ceiling in a random location
    2 means a closed door is opened
    3 means an item appears
    */
    boolean movable=false;//If false, it will not let move from being true
    boolean move=false;//If true, it will allow the block to be pushed
    public Block(boolean b, int e, int k){
        movable=b;
        move=b;
        event=e;
        keypos=k;
    }
    public void act(){
        if (movable==false){move=false;}
        if (move==true){collisionDetection();}
        
        if (event==-1&&moves==0){move=true;}
        
        if (moves!=0){
            setLocation(getX()+xmove,getY()+ymove);
            moves--;
        }else{
            xmove=0;
            ymove=0;
        }
        //This will activate an event if one has been specified
        if (move==false&&moves==0&&event>0){
            if (event==1){key();
            
                }if(event == 2){openDoor();}
        }
    }
    public void collisionDetection()
    {
        Class[] objects = {Link.class}; //All classes in this array are able to push this block
        int collisionAmount=0;
        int m=2; //How many cells the block travels every movement
        while (collisionAmount<objects.length){
            int o=13;//range of cells the object has to be within in a givin axis order to push the object
            //Down check
            Actor down = getOneObjectAtOffset(0, getImage().getHeight()/2+2,objects[collisionAmount]);
            if (down!=null&&down.getX()>getX()-o&&down.getX()<getX()+o&&Greenfoot.isKeyDown("w")){ymove=-m;}
            //Up check
            Actor up = getOneObjectAtOffset(0, -getImage().getHeight()/2-2,objects[collisionAmount]);
            if (up!=null&&up.getX()>getX()-o&&up.getX()<getX()+o&&Greenfoot.isKeyDown("s")){ymove=m;}
            //Left check
            Actor left = getOneObjectAtOffset(-getImage().getWidth()/2-2, 0,objects[collisionAmount]);
            if (left!=null&&left.getY()>getY()-o&&left.getY()<getY()+o&&Greenfoot.isKeyDown("d")){xmove=m;}
            //Right check
            Actor right = getOneObjectAtOffset(getImage().getWidth()/2+2, 0,objects[collisionAmount]);
            if (right!=null&&right.getY()>getY()-o&&right.getY()<getY()+o&&Greenfoot.isKeyDown("a")){xmove=-m;}
            collisionAmount++;
        }
        collisionAmount=0;
        
        /**This checks if there is an object in the blocks way when it moves*/
        
        Class[] blocks = {Link.class,Wall.class,Block.class,Enemy.class}; //All classes in this array can prevent the block from moving
        Class[] specialBlocks = {Lava.class,Water.class}; // These Classes can interact With Blocks or are special in Some Way
      
          while (collisionAmount<blocks.length){
            //Down check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i++){
                Actor object = getOneObjectAtOffset(i, getImage().getHeight()/2+20,blocks[collisionAmount]);
                if (object!=null&&ymove>0){negateMoving();}
            }
            //Up check
            for (int i=-getImage().getWidth()/2+2; i<getImage().getWidth()/2-2; i++){
                Actor object = getOneObjectAtOffset(i, -getImage().getHeight()/2-20,blocks[collisionAmount]);
                if (object!=null&&ymove<0){negateMoving();}
            }
            //Left check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i++){
                Actor object = getOneObjectAtOffset(-getImage().getWidth()/2-20, i,blocks[collisionAmount]);
                if (object!=null&&xmove<0){negateMoving();}
            }
            //Right check
            for (int i=-getImage().getHeight()/2+2; i<getImage().getHeight()/2-2; i++){
                Actor object = getOneObjectAtOffset(getImage().getWidth()/2+20, i,blocks[collisionAmount]);
                if (object!=null&&xmove>0){negateMoving();}
            }
            collisionAmount++;
        }
         
         // Collision for Blocks and Water/Lava
                Actor lavaCollideBlock = getOneObjectAtOffset(0,0,Lava.class);
                Actor waterCollideBlock = getOneObjectAtOffset(0,0,Water.class);
                 if(lavaCollideBlock!= null){ // Collision for the Lava and Block
                getWorld().removeObject(this);
                getWorld().removeObject(lavaCollideBlock);
                 return;
                }if(waterCollideBlock!=null){ // Collision for the Water and Block
                getWorld().removeObject(this);
                getWorld().removeObject(waterCollideBlock);
                 return;
                }
                
               
       
        if (xmove!=0||ymove!=0){move=false; moves=20;} //Moves is the amount of times the block will move before stopping
    }
   
    public void negateMoving(){
        xmove=0;
        ymove=0;
    }
    public void openDoor(){
       
    }
    public void key(){
        int x=0;
        int y=0;
        if (keypos==-1){keypos=Greenfoot.getRandomNumber(5);}
        if (keypos==0){x=8; y=6;}
        if (keypos==1){x=2; y=9;}
        if (keypos==2){x=14; y=9;}
        if (keypos==3){x=14; y=2;}
        if (keypos==4){x=2; y=2;}
        getWorld().addObject(new Key(),x*40+20,getWorld().getHeight()-y*40-20);
        event=0;
    }
    
}
