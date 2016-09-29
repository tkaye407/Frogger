import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class YouWin
{
    BufferedImage image;
    
    public YouWin()
    {
        try
        {
            image = ImageIO.read(new FileInputStream("youwin.png"));
        }
        catch(Exception e){System.out.println(e);}
        
        
    }

    public static void main(String[] args) 
    {
    }
    
    public void drawMe(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
    
    
}