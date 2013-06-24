import java.io.*;
import javax.sound.sampled.*;

public class Music {
    Clip music;
    AudioInputStream stream;
    File file;
    String filename;
    
    public void start(){
        if(!this.music.isRunning()) this.music.start(); 
    }
    
    public void stop(){
        this.music.stop();
        this.music.close();
    }
    
    
    /** Sets the clip to s, for loop number of times
     * 
     * @param String s
     * @param int loop
     */
    public void setClip(String s, int loop){
        try {
            this.filename = s;
            this.file = new File(s);
            this.stream = AudioSystem.getAudioInputStream(this.file);
            this.music = AudioSystem.getClip();
            this.music.open(this.stream);
            this.music.loop(loop);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void shootSound(int loop){
        this.stop();
        this.setClip("dinosaur.wav",loop);
        this.start();
    }
    
    
    
    
}