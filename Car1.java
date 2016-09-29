import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Car1
{
    BufferedImage image;
    int x, y, velocity;

   
    public Car1(int x1, int y1, int velocity1)
    {
        try
        {
            image = ImageIO.read(new FileInputStream("car1.png"));
        }
        catch(Exception e){System.out.println(e);}

        x = x1;
        y = y1;
        velocity = velocity1;

    }
    public static void main(String[] args) 
    {
    }
    public Rectangle getBounds()
    {
        //space that this ocupies
        Rectangle r = new Rectangle (x, y, (image.getWidth()-5), (image.getHeight()-5));
        return r;
    }

    public void drawMe(Graphics g)
    {
        g.drawImage(image, x, y, null);
    }

}
