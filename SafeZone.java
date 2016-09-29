import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class SafeZone
{
    BufferedImage image;
    int x, y, height, width;

   
    public SafeZone(int x1, int y1, int width1, int length1)
    {
        x = x1;
        y = y1;
        width = width1;
        height = length1;
        

    }

    public Rectangle getBounds()
    {
        //space that this ocupies
        Rectangle r = new Rectangle (x, y, width, height);
        return r;
    } 
    
    public static void main(String[] args) 
    {
    }
    
}
