import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FroggerUI implements KeyListener, ActionListener 
{
    FroggerCanvas canvas = new FroggerCanvas();
    //JButton button2 = new JButton("I'm #2");
    long initialTime = System.currentTimeMillis();
    long score = 0;
    JLabel Score = new JLabel("Total Score: " +  score);
    JLabel Lives = new JLabel("Number of Lives: " + canvas.frog.lives);
    JButton restartButton = new JButton("Play Again?");
    //JLabel winner = new JLabel("Current Winner:  ");
    //Auctioneer auctioneer = new Auctioneer(this);
    boolean leftKey = false; 
    boolean rightKey = false;
    boolean upKey = false;
    boolean downKey = false;
    boolean running = true;
    boolean game = true;
    boolean playAgain = true;
    int timesPlayed = 3;
    JFrame frame = new JFrame("Frogger");
    JPanel topPanel = new JPanel();
    YouWin youwin = new YouWin();
    YouLose youlose = new YouLose();

    public static void main(String[] args) 
    {
        FroggerUI fu = new FroggerUI();
    }
    
    public FroggerUI()
    {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        topPanel.setLayout( new GridLayout(1,1) );
        JPanel thePanel = new JPanel();
        thePanel.setLayout( new BorderLayout() );

        topPanel.add(canvas);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(Score);
        bottomPanel.add(Lives);
        bottomPanel.add(restartButton);
        thePanel.add(topPanel, BorderLayout.CENTER);
        thePanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(thePanel);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        //setting focus to main panel
        //creating 2 panels to place in frame

        frame.setVisible(true);
        frame.addKeyListener(this);
        restartButton.addActionListener(this);
        //restartButton.addActionListener(this);
        //button2.addActionListener(this);

        while(canvas.frog.lives > 0)
        {
            if(running)
            {
                canvas.repaint();

                //System.out.println(canvas.killFrog);            
                score = 1000 - ((System.currentTimeMillis() - initialTime)/100);

                Score.setText("Total Score: " + score);
                Lives.setText("Number of Lives: " + canvas.frog.lives);
                canvas.inCanoe = false;
                //JLabel Score = new JLabel("Total Score: " + score);

                //move frog more fluidly
                if (leftKey == true)
                {
                    canvas.frog.x = canvas.frog.x - 7;
                }
                if (rightKey == true)
                {
                    canvas.frog.x = canvas.frog.x + 7;
                }
                if (upKey == true)
                {
                    canvas.frog.y = canvas.frog.y - 7;
                }
                if (downKey == true)
                {
                    canvas.frog.y = canvas.frog.y + 7;
                }

                //if frog is killed (boolean in canvas) --> run methof killfrog
                if (canvas.killFrog == true)
                {
                    killFrog();
                }
                //iff boolean winGame in Canvas is true --> run method winGmame()
                if (canvas.winGame == true) 
                {
                    winGame();
                }
            }
            if (canvas.gameOver == true)
            {
                System.exit(0);
            }
            try { Thread.sleep(50);}
            catch(InterruptedException e) {}
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        //replay button
        if( e.getSource() == restartButton )
        {
            //replay game
            frame.requestFocusInWindow();
            canvas.frog.x = 300;
            canvas.frog.y = 620;
            running = true;
            canvas.killFrog = false; 
        }
    }
    //change the labels

    public void keyReleased(KeyEvent k) 

    {
        //when keys are released --> make booleans false to stop moving
        if(k.getKeyCode() == KeyEvent.VK_RIGHT)
        {   
            rightKey = false;
        }
        if(k.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftKey = false;
        }
        if(k.getKeyCode() == KeyEvent.VK_UP)
        {
            upKey = false;
        }
        if(k.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downKey = false;
        }
    }

    public void keyTyped(KeyEvent k) 
    {}

    public void keyPressed(KeyEvent k) 
    {
        //when keys are pressed --> make booleans true in order to move
        if(k.getKeyCode() == KeyEvent.VK_RIGHT)
            rightKey = true;
        if(k.getKeyCode() == KeyEvent.VK_LEFT)
            leftKey = true;
        if(k.getKeyCode() == KeyEvent.VK_DOWN)
            downKey = true;
        if(k.getKeyCode() == KeyEvent.VK_UP)
            upKey = true;
        //if(k.getKeyCode() == KeyEvent.VK_SPACE)
        //  canvas.add10Stars();
    }

    public void killFrog()
    {
        //method when frog dies;

        canvas.frog.lives --; 
        if (canvas.frog.lives <=0)
        {
            //end game
            running = false;
            System.out.println("YOU LOOSSEEEEE! YOU SUCK!!! THE GAME IS OVER!");
            canvas.lose = true;

            //System.exit(0);

        }
        else
        {
            //restart
            running = false;
            //System.out.println("You have " + canvas.frog.lives + " lives left.");
        }

    }

    public void winGame()
    {
        //meethod to win game
        if (timesPlayed >0)
        {
            try { Thread.sleep(3000);}
            catch(InterruptedException e) {}
            running = false;
            canvas.winGame = false;
            canvas.killFrog = false;
            canvas.frog.x = 300;
            canvas.frog.y = 620;
            timesPlayed--;

            System.out.println(" Congratulations!!! YOU WINNNNNNN!!!!!! PROCEED TO THE NEXT LEVEL");
            
            frame.requestFocusInWindow();

            running = true;
        }
        if (timesPlayed  <= 0)
        {
            running = false;
            canvas.youWinForReal = true;

        }
    }

    //System.exit(0);
}

