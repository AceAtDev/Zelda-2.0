import greenfoot.*;
import java.util.List;

public class RandomlyGeneratingDungeon extends World
{   
    /**
     * Constructor for objects of class RandomlyGeneratingDungeon.
     * 
     */
    //final int DistanceBetweenBlocks = 40;
    Link player = new Link();
    public RandomlyGeneratingDungeon()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
        super(680, 480, 1, false); 
        

        addObject(player,getWidth()/2,getHeight()/2+90);
        addObject(new FadeOverlay(),getWidth()/2,getHeight()/2);
        //addObject(new HealthUI(0,0, player),getWidth()/2,getHeight()/2);
        addObject(new Animation(player),player.getX(),player.getY());
        paintOrder();
        generateDungeon();
        dungeonObjects();
        generateDungeonEnemies();
        

    }
    
    Class[] all = {Wall.class,Key.class,Block.class,Lava.class,Water.class, Enemy.class};
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
       // Right Wall (Bottom Half)
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
        
        // Room 5 Walls 
        // Top Wall
        addObject(new Wall((getWidth()*2)-40,40),2060,-460);
        // Bottom Wall
        addObject(new Wall((getWidth()*2)-40,40),2060,-20);
        // Left Wall (Top Half) -> Entrance From Room 3
        addObject(new Wall(40,(getHeight()/2)-40),1380,-380);
        // Left Wall (Bottom Half) 
        addObject(new Wall(40,(getHeight()/2)-40),1380,-100);
        // Right Wall
        addObject(new Wall(40,getHeight()-40),2700,-260);
    }
    public void paintOrder(){ // Order of displaying classes ( Makes the Transition from room to room smoother
        setPaintOrder(projectile.class ,Animation.class,Link.class,FadeOverlay.class,Wall.class,Key.class,Block.class,Lava.class,Water.class);
    }

    public void act(){
        paintOrder();
        if (tileset==0)setBackground(new GreenfootImage("GreenTile.png"));
        if (tileset==1)setBackground(new GreenfootImage("BlueTile.png"));
        if (tileset==2)setBackground(new GreenfootImage("AquaTile.png"));
        if (tileset==3)setBackground(new GreenfootImage("YellowTile.png"));
        if (tileset==4)setBackground(new GreenfootImage("GreyTile.png"));
        
       System.out.println(player.getX());
       System.out.println(player.getY());
    }
    
    public void dungeonObjects(){
        // Room One Objects
        block(8,4,true,-1);
        block(7,3,true,-1);
        block(9,3,true,-1);
        block(8,2,true,-1);
        
        block(1,9);
        block(2,9);
        block(3,9);
        block(4,9);
        block(5,9);
        block(6,9);
        block(7,9);
        block(8,9);
        block(9,9);
        block(10,9);
        block(11,9);
        block(12,9);
        block(13,9);
        block(14,9);
        block(15,9);
        
        lava(1,5);
        lava(1,4);
        
        
        water(1,2);
        water(1,1);
        
        
        // Room 2 Objects
        
        block(18,7);
        block(19,7,true,-1);
        block(20,7);
        block(21,7);
        block(22,7);
        block(23,7);
        block(23,8);
        block(23,9);
        block(23,10,true,-1);
        block(27,10);
        block(27,9);
        block(27,8,true,-1);
        block(27,7);
        block(28,7,true,-1);
        block(29,7);
        block(30,7,true,-1);
        block(31,7);
        block(32,7,true,-1);
        block(33,7);
        block(18,4);
        block(19,4);
        block(20,4,true,-1);
        block(21,4);
        block(22,4,true,-1);
        block(23,4);
        block(27,4);
        block(28,4);
        block(29,4);
        block(30,4);
        block(31,4,true,-1);
        block(32,4,true,-1);
        block(23,3);
        block(23,2,true,-1);
        block(23,1);
        block(27,3,true,-1);
        block(27,2,true,-1);
        block(27,1,true,-1);
        
        water(18,10);
        water(32,10);
        water(18,1);
        water(32,1);
        
        // Room 3 Objects
        
        water(18,14);
        water(19,14);
        water(18,13);
        water(19,13);
        water(20,14);
        water(20,13);
        
        water(32,22);
        water(32,21);
        water(31,22);
        water(31,21);
        water(30,21);
        water(30,22);
    
        
        lava(18,22);
        lava(18,21);
        lava(19,22);
        lava(19,21);
        lava(20,21);
        lava(20,22);
        
        lava(32,13);
        lava(32,14);
        lava(31,14);
        lava(31,13);
        lava(30,13);
        lava(30,14);
        
        // Room 4 Objects
        
        block(15,16);
        block(14,16);
        block(13,16);
        block(12,16);
        block(11,16);
        block(10,16);
        block(9,16); 
        block(8,16);
        block(7,16);
        block(6,16);
        block(5,15);
        block(4,15);
        block(3,15);
        block(2,15);
        block(1,15);
        
        block(15,19);
        block(14,19);
        block(13,19);
        block(12,19);
        block(11,19);
        block(10,19);
        block(9,19); 
        block(8,19);
        block(7,19);
        block(6,19);
        block(5,20);
        block(4,20);
        block(3,20);
        block(2,20);
        block(1,20);

      water(1,14);
      water(1,13);
      water(2,13);
      water(2,14);
      water(3,13);
      water(3,14);
      water(4,13);
      water(4,14);
      water(5,13);
      water(5,14);
      water(6,13);
      water(6,14);
      water(6,15);
      water(7,13);
      water(7,14);
      water(7,15);
      water(8,13);
      water(8,14);
      water(8,15);
      water(9,13);
      water(9,14);
      water(9,15);
      water(10,13);
      water(10,14);
      water(10,15);
      water(11,13);
      water(11,14);
      water(11,15);
      water(12,13);
      water(12,14);
      water(12,15);
      water(13,13);
      water(13,14);
      water(13,15);
      water(14,13);
      water(14,14);
      water(14,15);
      water(15,13);
      water(15,14);
      water(15,15);

      
      water(1,21);
      water(1,22);
      water(2,21);
      water(2,22);
      water(3,21);
      water(3,22);
      water(4,21);
      water(4,22);
      water(5,21);
      water(5,22);
      water(6,21);
      water(6,22);
      water(6,20);
      water(7,22);
      water(7,21);
      water(7,20);
      water(8,20);
      water(8,21);
      water(8,22);
      water(9,20);
      water(9,21);
      water(9,22);
      water(10,20);
      water(10,21);
      water(10,22);
      water(11,20);
      water(11,21);
      water(11,22);
      water(12,20);
      water(12,21);
      water(12,22);
      water(13,20);
      water(13,21);
      water(13,22);
      water(14,20);
      water(14,21);
      water(14,22);
      water(15,20);
      water(15,21);
      water(15,22);









    
    }
    
    
    
    // if one of the object collides with other, then the battle will start
    Class[] battleStarters = {Link.class, Enemy.class};
    private void prepareBattle()
    {
        
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
    static int  tileset = 0;
    public static void changeTileSet(int i){
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
        // Room one tutorial Enemy
        addObject(new Enemy(3,1,2, 0.3, false), 550, 340);
        // Room 3 Enemy Gauntlet
        addObject(new Enemy(3,3,2,0.25, true),1020,-220);
        addObject(new Enemy(5,5,0,0.1, false),750,-240);
        addObject(new Enemy(2,3,2,0.19, true),1240,-220);
        
    }
    
    //Dungeon Tile Methods
    
    public void block(int x, int y, boolean movable, int event, int keypos){
        
        addObject(new Block(movable,event,keypos),x*40+20,getHeight()-y*40-20);
       
    }
    public void block(int x, int y, boolean movable, int event){
        
        addObject(new Block(movable,event,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y, boolean movable){
        
        addObject(new Block(movable,0,-1),x*40+20,getHeight()-y*40-20);
    }
    public void block(int x, int y){
       
        addObject(new Block(false,0,-1),x*40+20,getHeight()-y*40-20);
    }
    //Liquids
    public void lava(int x, int y){
        
        addObject(new Lava(),x*40+20,getHeight()-y*40-20);
    }
    public void water(int x, int y){
        
        addObject(new Water(),x*40+20,getHeight()-y*40-20);
    }
}
