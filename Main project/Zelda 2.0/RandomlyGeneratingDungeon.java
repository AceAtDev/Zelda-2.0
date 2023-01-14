import greenfoot.*;
import java.util.List;

public class RandomlyGeneratingDungeon extends World
{   
    /**
     * Constructor for objects of class RandomlyGeneratingDungeon.
     * 
     */
    //final int DistanceBetweenBlocks = 40;
    public RandomlyGeneratingDungeon()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(680, 480, 1, false); 
        addObject(new Link(),getWidth()/2,getHeight()/2+20);
        addObject(new FadeOverlay(),getWidth()/2,getHeight()/2);
        
        generateDungeon();
        dungeonObjects();
        paintOrder();
    }
    
    Class[] all = {Wall.class,Key.class,Block.class,Lava.class,Water.class};
    public void scroll(String direction){
        int v=0;
        int h=0;
        if (direction.equals("left"))h=getWidth();
        if (direction.equals("right"))h=-getWidth();
        if (direction.equals("up"))v=getHeight();
        if (direction.equals("down"))v=-getHeight();
        int a=0;
        while (a<all.length){
            List object = getObjects(all[a]);
            if (! object.isEmpty()){
                for (int i=0; i<object.size(); i++){
                    Actor Object = (Actor) object.get(i);
                    Object.setLocation(Object.getX()+h,Object.getY()+v);
                }
            }
            a++;
        }
    }
    public void generateDungeon(){ // Generates the Dungeon's Layout  
        // Wall Constructors (Used for Generating the Dungeon Layout)
        
        // Room 1 Walls
        
        // Top Wall 
       addObject(new Wall(getWidth(),40),340,20);
        // Bottom Wall
       addObject(new Wall(getWidth(),40),340,460);
        // Left Wall
       addObject(new Wall(40,getHeight()-65),20,245);
       // Right Wall (Top Half) -> Used as a Doorway Exit
       addObject(new Wall(40,(getHeight()/2)-40),660,100);
       // Right Wall (Bottom Half)
       addObject(new Wall(40,(getHeight()/2)-40),660,380);
        
       // Room 2 Walls
       // Top Wall (Left Half) -> Door Way to Room 3
       addObject(new Wall((getWidth()/2)-60,40),820,20);
       // Top Wall (Right Half) 
       addObject(new Wall((getWidth()/2)-60,40),1220,20);
       // Bottom Wall
       addObject(new Wall(getWidth(),40),1020,460);
       // Left Wall (Top Half) -> Entry from Room 1
       addObject(new Wall(40,(getHeight()/2)-40),700,100);
       // Left Wall (Bottom Half) 
       addObject(new Wall(40,(getHeight()/2)-40),700,380);
       // Right Wall 
       addObject(new Wall(40,getHeight()-65),1340,245);
       
       // Room 3 Walls
       // Top Wall
       addObject(new Wall(getWidth(),40),1020,-460);
       // Bottom Wall (Left Half) -> Door Way From Room 2
       addObject(new Wall((getWidth()/2)-60,40),820,-20);
       // Bottom Wall (Right Half) 
       addObject(new Wall((getWidth()/2)-60,40),1220,-20);
       // Left Wall (Top Half)
       addObject(new Wall(40,(getHeight()/2)-40),700,-380);
       // Left Wall (Bottom Half)
       addObject(new Wall(40,(getHeight()/2)-40),700,-100);
       // Right Wall (Top Half)
       addObject(new Wall(40,(getHeight()/2)-40),1340,-380);
       addObject(new Wall(40,(getHeight()/2)-40),1340,-100);
        
        // Room 4 Walls
        // Top Wall
         addObject(new Wall(getWidth(),40),340,-460);
        // Bottom Wall
         addObject(new Wall(getWidth(),40),340,-20);
        // Left Wall
        addObject(new Wall(40,getHeight()-40),20,-260);
        // Right Wall (Top Half)
        addObject(new Wall(40,(getHeight()/2)-40),660,-380);
        // Right Wall (Bottom Half)
        addObject(new Wall(40,(getHeight()/2)-40),660,-100);
    }
    public void paintOrder(){
        setPaintOrder(Link.class,FadeOverlay.class,Wall.class,Key.class,Block.class,Lava.class,Water.class);
    }
    
    public void act(){
        paintOrder();
        if (tileset==0)setBackground(new GreenfootImage("GreenTile.png"));
        if (tileset==1)setBackground(new GreenfootImage("BlueTile.png"));
        if (tileset==2)setBackground(new GreenfootImage("AquaTile.png"));
        if (tileset==3)setBackground(new GreenfootImage("YellowTile.png"));
        if (tileset==4)setBackground(new GreenfootImage("GreyTile.png"));
    }
    
    public void dungeonObjects(){
        // Room One Objects
        block(4,6,true,-1);
        block(3,6,true,1,2);
        block(5,6);
        block(4,7);
        block(4,5);
        
        
    
        
        lava(12,9);
        lava(14,10);
        lava(13,10);
        lava(13,8);
        lava(14,8);
        
       
        
        
    }
    
    /**
    Tile Sets
    ---------
    0 = Green
    1 = Blue
    2 = Aqua
    3 = Yellow
    4 = Grey
    */
    int tileset = 0;
    public void changeTileSet(int i){
        tileset=i;
    }
    
    //Dungeon Manipulation methods
    
    public void nextRoom(int roomID){
        
    }
    public void generateDungeonRoom(){
        
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
    public void generateDungeonEnemies(){
        
    }
    
    //Dungeon Tile Methods
    
    public void block(int x, int y, boolean movable, int event, int keypos){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(movable,event,keypos),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y, boolean movable, int event){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(movable,event,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y, boolean movable){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(movable,0,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: BLOCK AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Block(false,0,-1),x*40+20,getHeight()-y*40-20);
    }
    //Liquids
    public void lava(int x, int y){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: LAVA AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Lava(),x*40+20,getHeight()-y*40-20);
    }
    public void water(int x, int y){
        if (x*40+20<0||x*40+20>getWidth()||getHeight()-y*40-20<0||getHeight()-y*40-20>getHeight()){
            System.out.println("ERROR: WATER AT ("+x+","+y+") IS OUT OF WORLD RANGE"); return;
        }
        addObject(new Water(),x*40+20,getHeight()-y*40-20);
    }
}
