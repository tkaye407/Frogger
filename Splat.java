import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Splat
{
    BufferedImage image;
    int x, y;

   
    public Splat(int x1, int y1)
    {
        try
        {
            image = ImageIO.read(new FileInputStream("Splat.png"));
        }
        catch(Exception e){System.out.println(e);}

        x = x1;
        y = y1;
        

    }

    
    public void drawMe(Graphics g)
    {
        g.drawImage(image, x, y, null);
    }
    
    public static void main(String[] args) 
    {
    }
}
