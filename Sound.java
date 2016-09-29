import javax.sound.sampled.*;
import java.io.*;

public class Sound
{
    AudioInputStream sample;
    Clip clip;

    public Sound(String f)
    {
        try 
        { 
            clip = AudioSystem.getClip(); 
            sample = AudioSystem.getAudioInputStream(new File(f)); 
            clip.open(sample);
        }
        catch(Exception e) { System.out.println(e); }   
    }

    public void play()
    {
        clip.setFramePosition(0);
        clip.loop(0);
    }
    public static void main(String[] args) 
    {
    }
}
