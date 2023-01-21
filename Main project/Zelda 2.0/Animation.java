import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animation here.
 * 
 * requires files to be named Something_UP2.png (name_directionFRAME.png)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation extends Actor
{
    private GreenfootImage[][] basicMan;
    private GreenfootImage[][] bonesMan;

    private GreenfootImage[][] playerImage;

    private Direction direction;
    private int frame;

    private double xx, yy;

    private long lastFrame;
    private double framesPerSecond; // animation speed
    private int moveSpeed; // per second, not act
    private double secondsPerFrame;

    private int walkSpeed;
    private int walkAnimSpeed;

    private int runSpeed;
    private int runAnimSpeed;
    
    private boolean idle;

    public Animation (){
        // 4 directions, 9 frames per direction
        //basicMan = new GreenfootImage[4][9];
        direction = Direction.RIGHT;

        walkSpeed = 20;
        walkAnimSpeed = 15;

        runSpeed = 40;
        runAnimSpeed = 30;

        framesPerSecond = walkAnimSpeed;
        moveSpeed = walkSpeed;

        secondsPerFrame = 1.0 / framesPerSecond;       
        lastFrame = System.nanoTime();

        frame = 0;
        idle = false;

        basicMan = importSprites ("W_", Direction.size, 9);
        bonesMan = importSprites ("S_", Direction.size, 9);

        playerImage = basicMan;

        setImage (playerImage[direction.getDirection()][0]);
    }

    private GreenfootImage[][] importSprites (String baseString, int nDirs, int nFrames){
        GreenfootImage[][] temp = new GreenfootImage[nDirs][nFrames];
        for (int dir = 0; dir < nDirs; dir++){
            for (int frm = 0; frm < nFrames; frm++){
                String directionString;
                switch (dir) {
                    case 0: directionString = "Right";  break;
                    case 1: directionString = "Left";   break;
                    case 2: directionString = "Up";     break;
                    case 3: directionString = "Down";   break; 
                    default: directionString = "Error"; break;
                }

                String tempFileName = baseString + directionString + frm +".png";
                temp[dir][frm] = new GreenfootImage (tempFileName);
            }
        }
        return temp;
    }

    public void addedToWorld (World w){
        xx = getX();
        yy = getY();
        setLocation ((int)Math.round(xx), (int)Math.round(yy));
    }

    /**
     * Act - do whatever the Walker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // determine how much time has passed since the last act
        long current = System.nanoTime();
        // Find elapsed time - in milliseconds (ms)
        long elapsed = (current - lastFrame) / 1000000; 

        // Each act, movement and idle state are reset
        int moveX = 0, moveY = 0;
        

        // Keyboard iput for pressed keys
        String key = Greenfoot.getKey();

        if (key != null){
            if (key.equals("b")){
                playerImage = bonesMan;
            }
            if (key.equals("n")){
                playerImage = basicMan;
            }
        }

        // Keyboard input for held-down movement keys
        if (Greenfoot.isKeyDown("shift")){  // run
            framesPerSecond = runAnimSpeed;
            moveSpeed = runSpeed;
        } else {                            // walk
            framesPerSecond = walkAnimSpeed;
            moveSpeed = walkSpeed;                   
        }

        // Recalculate animation speed, in case run/walk has changed
        secondsPerFrame = 1.0 / framesPerSecond;

        // For each key...
        if (Greenfoot.isKeyDown("right")){
            moveX = 1; // set direction
            if (direction != direction.RIGHT){ // if I wasn't already moving this direction...
                frame = 1; // start again at frame 1
                direction = direction.RIGHT; // set direction to the newly specified direction
            }
        }
        if (Greenfoot.isKeyDown("left")){
            moveX = -1;
            if (direction != direction.LEFT){
                frame = 1;
                direction = direction.LEFT;
            }
        }
        if (moveX == 0){ // prevent diagonal movement - only check for Y if nothing is moving on X
            if (Greenfoot.isKeyDown("up")){
                moveY = -1;
                if(direction != direction.UP){
                    frame = 1;
                    direction = direction.UP;
                }
            }
            if (Greenfoot.isKeyDown("down")){
                moveY = 1;
                if (direction != direction.DOWN){
                    frame = 1; 
                    direction = direction.DOWN;
                }
            }
        }

        if (moveX == 0 && moveY == 0){ // if not moving, switch to idle
            idle = true;
            lastFrame = current;    // reset animation timer so it always starts fresh in next frame if
                                    // next frame is animated
            frame = 0;
            setImage (playerImage[direction.getDirection()][frame]);
        }        
        else{
            //System.out.println("System Nano: " + System.nanoTime());
            //System.out.println("Last: " + lastFrame);
            //System.out.println("Elapsed: " + elapsed);
            // Check if ready to show next frame, and if so, advance frame
            if (elapsed > secondsPerFrame * 1000 || idle == true){
                // note - the use of the idle variable here is to avoid restarting the animation
                // timer after idle. This way, the first frame after idle starts instantly
                lastFrame = current;
                frame++;
                idle = false;
            }

            if (frame > playerImage[direction.getDirection()].length - 1){
                frame = 1; // 0th frame is idle frame only, so count 1..last, not 0..last
            }
            // now that the calculations are done, set the correct image
            setImage (playerImage[direction.getDirection()][frame]);
        }
        // calculate exact new location. Decimal values will be rounded, but stored accurately, for
        // smooth animation over time
        xx += ((double)(moveX) * moveSpeed) * (elapsed / 1000.0);
        yy += ((double)(moveY) * moveSpeed) * (elapsed / 1000.0);

        // Normalize position - makes sure it can't go outside of world
        if (xx > getWorld().getBackground().getWidth() || xx < 0){
            xx = (double)getX();
        }
        if (yy > getWorld().getBackground().getHeight() || yy < 0){
            yy = (double)getY();
        }

        //setImage (playerImage[direction.getDirection()][frame]);
        //setLocation ((int)Math.round(xx), (int)Math.round(yy));

    }    

    // ENUM to keep direction related code clean. The array is set up so that 
    // index of main array corresponds with direction (I.e. img[0][2] is the 2nd frame in the RIGHT array)
    // which could also be written img[direction.getDirection()][2] to check the direction variable, of type
    // Direction, and use the corresponding int to access the correct image in the 2d array.
    public enum Direction {
        RIGHT(0), 
        LEFT(1), 
        UP(2), 
        DOWN(3);

        private final int dirCode;
        private Direction (int dirCode){
            this.dirCode = dirCode;
        }

        public int getDirection (){
            return this.dirCode;
        }

        public final static int size = Direction.values().length;
    }    

}
