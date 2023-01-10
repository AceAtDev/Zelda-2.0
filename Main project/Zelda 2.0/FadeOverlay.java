import greenfoot.*;
public class FadeOverlay extends Actor
{
    int opacity=255;//Max opacity is 255
    boolean fadeIn=true;//Subtract Opacity
    boolean fadeOut=false;//Increase Opacity
    boolean slowFadeIn=false;
    boolean slowFadeOut=false;
    boolean darkRoom=false;
    
    public void addedToWorld(World world){
        getImage().scale(getWorld().getWidth()*2,getWorld().getHeight()*2);
        setLocation(getWorld().getWidth()/2,getWorld().getHeight()/2);
    }
    public void act() 
    {
        if (fadeIn==true){opacity-=15;}
        if (fadeOut==true){opacity+=15;}
        if (slowFadeIn==true){opacity--;}
        if (slowFadeOut==true){opacity++;}
        if (darkRoom==true){opacity+=15;}
        if (opacity>255){opacity=255; resetBooleans();}
        if (opacity<=0){opacity=0; resetBooleans();}
        if (opacity<250&&darkRoom==true){opacity=250; resetBooleans();}
        getImage().setTransparency(opacity);
    }
    
    public void nextRoom(){
        
    }
    public void darkRoom(){
        resetBooleans();
        darkRoom=true;
    }
    
    public void resetBooleans()
    {
        fadeIn=false;
        fadeOut=false;
        slowFadeOut=false;
        slowFadeIn=false;
        darkRoom=false;
    }
    public void fadeIn()
    {
        resetBooleans();
        fadeIn=true;
    }
    public void fadeOut()
    {
        resetBooleans();
        fadeOut=true;
    }
    public void slowFadeIn()
    {
        resetBooleans();
        slowFadeIn=true;
    }
    public void slowFadeOut()
    {
        resetBooleans();
        slowFadeOut=true;
    }
}
