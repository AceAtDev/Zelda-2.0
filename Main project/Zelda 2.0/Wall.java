import greenfoot.*;

public class Wall extends Actor
{
    public Wall(int width, int height)
    {
        GreenfootImage ground = new GreenfootImage("GreyBlock.png");
        GreenfootImage image = new GreenfootImage(width, height);
        int w=ground.getWidth();
        int h=ground.getHeight();
        for(int offset=0; offset<width; offset+=w) image.drawImage(ground, offset, 0);
        for(int offset=0; offset<height; offset+=h) image.drawImage(ground, 0, offset);
        setImage(image);
    }
}
