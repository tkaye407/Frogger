import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class NextLevel
{
    BufferedImage image;

    public NextLevel()
    {
        try
        {
            image = ImageIO.read(new FileInputStream("nextLevel.png"));
        }
        catch(Exception e){System.out.println(e);}
        
        
    }
    
    public void drawMe(Graphics g)
    {
        g.drawImage(image,0,0, null);
    }
    
    public static void main(String[] args) 
    {
    }
}