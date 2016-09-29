import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Frog
{
    BufferedImage image;
    int x = 300;
    int y = 610;
    
    int lives = 5;
    public Frog()
    {
        try
        {
            image = ImageIO.read(new FileInputStream("frog.png"));
        }
        catch(Exception e){System.out.println(e);}
        
        
    }
public static void main(String[] args) 
    {
    }
    public Rectangle getBounds()
    {
        //space that this ocupies
        Rectangle r = new Rectangle (x, y, image.getWidth()-5, image.getHeight()-5);
        return r;
    }
    
    public void drawMe(Graphics g)
    {
        g.drawImage(image, x, y, null);
    }
    
    
}