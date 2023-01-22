import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Actor;

/**
 * Write a description of class BattleManager here.
 * 
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleManager extends Actor
{
    
    private int savedPlayerX;
    private int savedPlayerY;
    
    private boolean inBattle = false;
    
    private Vector2D playerBattlePos = new Vector2D();
    private Vector2D enemyBattlePos = new Vector2D();
    
    int playerXOffset = -115 * 5; //to the left
    int playerYOffset = 0;
    
    int enemyXOffset = -115;
    int enemyYOffset = 0;
    
    //private Vector2D playerLastPos = new Vector2
    
    boolean playerTurn = true;
    boolean enemyTurn = false;
    
    Link player = null;
    Enemy caughtEnemy = null;
    
    
    
    
    GreenfootSound battleBackgroundMusic = new GreenfootSound("Violet Stars - battle.wav");// BattleMusic
    
    
    public BattleManager(int playerX, int playerY, int enemyX, int enemyY, Link player) // Set up battle positions
    {
        this.playerBattlePos = new Vector2D(playerX + playerXOffset, playerY);
        this.enemyBattlePos = new Vector2D(enemyX + enemyXOffset, enemyY);
        this.player = player;
    }
    
    
    
    public void act()
    {
        // check if there is battle
        
    }
    
    public void battleUpdater()
    {
        // Start battle music
        //Greenfoot.playSound("");
        
        //battleBackgroundMusic.playLoop();
        
        if(playerTurn)
        {
            if(Greenfoot.isKeyDown("e"))
            {
                Greenfoot.playSound("Normal Attack-HQ.wav");
                caughtEnemy.takeDamage(player.attack());
                Greenfoot.delay(10);
                playerTurn = false;
                enemyTurn = true;
            }
        }
        else if(enemyTurn)
        {
            
            enemyTurn = false;
            playerTurn = true;
        }
        
        
        
                
        
        
        
        if(!caughtEnemy.getIsAlive())
        {
            // turn off music one dead
            battleBackgroundMusic.stop();
            endBattle();
        }
        else if(!caughtEnemy.getIsAlive())
        {
            // turn off music one dead
            battleBackgroundMusic.stop();
            //Show lose screen
        }
    }
    
    private void endBattle()
    {
        
        //caughtEnemy.getWorld().removeObject(caughtEnemy); // Kill enemy
              
        
        //Return the player back to the last pos, OP: make a lerp animaiton
        player.setLocation(
                    savedPlayerX,
                    savedPlayerY
                    );
                    
        resetBooleans();
        player.hitEnemy = null;
        player.endedBattle();
    }
    
    public void battleStart() // called once/Used externally
    {
        
        //Link player = getWorld().getObjects(Link.class).get(0); // find the player
        caughtEnemy = player.getHitEnemy(); // get the enemy that the player challenged
        
        savePos(player.getX(), player.getY()); // Save Link pos
        
        
        player.inBattle();
        caughtEnemy.battleStarted();
        
        player.setLocation(playerBattlePos.x(),playerBattlePos.y()); // Set Player in battle pos
        caughtEnemy.setLocation(enemyBattlePos.x(),enemyBattlePos.y()); // Set Enemy in battle pos
        

        
        battleUpdater();
        
        
    }
    
    public boolean playerAttacked()
    {
        enemyTurn = true;
        return playerTurn = false;
    }
    
    public boolean enemyAttacked()
    {
        playerTurn = true;
        return enemyTurn = false;
    }

    public void resetBooleans()
    {
        playerTurn = true;
        enemyTurn = false;
    }
    
    public boolean getInBattle()
    {
        return inBattle;
    }
    
    
    public void savePos(int x, int y)
    {
        savedPlayerX = x;
        savedPlayerY = y;
    }
    
    public Vector2D getLastPos()
    {
        return new Vector2D(savedPlayerX, savedPlayerY);
    }
    
    public boolean isInBattle()
    {
        return inBattle;
    }
    

}
