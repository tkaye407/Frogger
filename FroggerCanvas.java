import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class FroggerCanvas extends Canvas
{
    //create array lists for Car1, Car2, Boats, and SafeZones
    ArrayList<Car1> ferraris = new ArrayList<Car1>();
    ArrayList<Car2> lambos = new ArrayList<Car2>();
    ArrayList<Boat> boats = new ArrayList<Boat>();
    ArrayList<SafeZone> safeZones = new ArrayList<SafeZone>();
    //instantiate soundfile
    Sound sound = new Sound("energy.wav");
    int i = 0;
    int k = 7;
    int bk = 7;
    //instantiate frog
    Frog frog = new Frog();
    YouWin youwin = new YouWin();
    NextLevel nextLevel = new NextLevel();
    //upload background
    Background background = new Background();
    //create rectangle to simulate water area
    Rectangle water = new Rectangle(0,65,700,220);
    //create booleans to control game operations
    boolean inCanoe = false;
    boolean killFrog = false;
    boolean winGame = false; 
    boolean youWinForReal = false;
    boolean gameOver = false;
    boolean lose = false;
    YouLose youlose = new YouLose();

    public static void main(String[] args) 
    {
    }
    
    public FroggerCanvas()
    {
        setSize(700,700);
        //create Ferraris
        while (i >= 0 && i <= k)
        {
            int xValue = (int)(Math.random()*700);
            int yValue = 580-90*(i%3);
            int cvelocity = (int) (Math.random()*15);
            Car1 f = new Car1(xValue, yValue, cvelocity);
            ferraris.add(f);
            i++;

        }
        i = 0;
        //Create Lambos
        while (i >= 0 && i <= k)
        {
            int xValue = (int)(Math.random()*700);
            int yValue = 525-90*(i%3);
            int cvelocity = (int) (Math.random()*15);
            Car2 l = new Car2(xValue, yValue, cvelocity);
            lambos.add(l);
            i++;

        }
        i = 0;
        //Create Boats going one way
        while (i >= 0 && i <= bk)
        {
            int xValue = (int)(Math.random()*700);
            int yValue = 75 + 90*(i%3);
            int cvelocity = (int) (Math.random()*12);
            Boat b1 = new Boat(xValue, yValue, cvelocity);
            boats.add(b1);
            i++;

        }
        i = 0;
        //Create boats going the other way
        while (i >= 0 && i <= bk)
        {
            int xValue = (int)(Math.random()*700);
            int yValue = 120+90*(i%2);
            int cvelocity = - (int) (Math.random()*10);
            Boat b2 = new Boat(xValue, yValue, cvelocity);
            boats.add(b2);
            i++;

        }
        //individually create safe zones and add them to arraylists 
        SafeZone s1 = new SafeZone(0, 0, 80, 70);
        SafeZone s2 = new SafeZone(140, 0, 80, 60);
        SafeZone s3 = new SafeZone(300, 0, 80, 60);
        SafeZone s4 = new SafeZone(460, 0, 80, 60);
        SafeZone s5 = new SafeZone(590, 0, 80, 60);

        safeZones.add(s1);
        safeZones.add(s2);
        safeZones.add(s3);
        safeZones.add(s4);
        safeZones.add(s5);

        //make many instances of Car1 and put them in ferraris
    }

    public void paint(Graphics g)
    {        
        //System.out.println("painting");
        //draw background --> frog box 
        background.drawBackground(g);
        Rectangle frogBox = frog.getBounds();
        g.setColor(Color.RED);

        /*
         * for (int i = 0; i<14; i++)
        {
        g.fillRect(50*i, 0 , 10, 10); 
        g.fillRect(0, 50*i , 10, 10); 

        }
         */

        for(Car1 c: ferraris)
        {
            //what to do for each ferrari = draw, getBounds, draw Rectange, move the car
            c.drawMe(g);
            c.x = c.x - c.velocity;

            Rectangle ferrariBox = c.getBounds();
            //when frog is hit by ferrari --> killFrog = true;
            if (frogBox.intersects(ferrariBox))
            {
                //System.out.println("***\ndead");
                Splat splat = new Splat(frog.x -30, frog.y -30);
                splat.drawMe(g);
                killFrog = true;
            }
            //if it runs off the page --> go to the other side
            if (c.x>700)
            {
                c.x = 0;
            }
            if (c.x<0)
            {
                c.x = 700;
            }
        }
        for(Car2 l: lambos)
        {
            //same
            l.drawMe(g);
            l.x = l.x + l.velocity;

            Rectangle lamboBox = l.getBounds();

            if (frogBox.intersects(lamboBox))
            {

                //System.out.println("***\ndead");
                Splat splat = new Splat(frog.x - 30, frog.y -30);
                splat.drawMe(g);
                killFrog = true;
            }

            if (l.x>700)
            {
                l.x = 0;
            }
            if (l.x<0)
            {
                l.x = 700;
            }
        }
        for(Boat b: boats)
        {
            //same
            b.drawMe(g);
            b.x = b.x + b.velocity;

            Rectangle boatBox = b.getBounds();

            if (frogBox.intersects(boatBox))
            {
                inCanoe = true;
                //make frog follow the moving boats
                frog.x = frog.x + b.velocity;

                //System.out.print("allgood..");

            }
            //else
            //{
            //inCanoe = false;
            //}

            if (b.x>700)
            {
                b.x = -30;
            }
            if (b.x<-30)
            {
                b.x = 700;
            }
        }
        for(SafeZone s: safeZones)
        {
            Rectangle safeZoneBox = s.getBounds();
            //when the frog enters the safe zone --> run method in UI (winGame())
            if (frogBox.intersects(safeZoneBox))
            {
                nextLevel.drawMe(g);

                winGame = true;

            }

        }
        //loop for the canoes
        //if frog touches a canoe
        //set inCanoe to true

        if ((frogBox.intersects(water)) && (inCanoe == false))
        {
            //When frog is in the water area but not the canoe --> killFrog
            Splat splat = new Splat(frog.x - 30, frog.y -30);
            splat.drawMe(g);
            killFrog = true;

        }
        //if the frog's box intersects the water && inCanoe is false
        //dead

        if(killFrog == false) frog.drawMe(g);

        //if frog runs off page --> go to other side
        if (frog.x >700)
        {
            frog.x = -30;
            sound.play();
        }

        if (frog.x<-30)
        {
            frog.x = 700;
            sound.play();
        }

        if (youWinForReal == true)
        {
            youwin.drawMe(g);
            try { Thread.sleep(3000);}
            catch(InterruptedException e) {}
 
            gameOver = true;
        }
       if (lose == true)
        {
            youlose.drawMe(g);
            try { Thread.sleep(2000);}
            catch(InterruptedException e) {}
            
            gameOver = true;
        }
    }
}

