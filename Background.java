import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Background
{
    BufferedImage image;
    
    public Background()
    {
        try
        {
            image = ImageIO.read(new FileInputStream("froggerBoard.png"));
        }
        catch(Exception e){System.out.println(e);}
        
        
    }

    public static void main(String[] args) 
    {
    }
    
    public void drawBackground(Graphics g)
    {
        g.drawImage(image, 0, 0,  null);
    }
    
    
}