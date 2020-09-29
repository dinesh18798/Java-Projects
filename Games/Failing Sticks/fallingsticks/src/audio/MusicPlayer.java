package audio;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Created by Dinesh on 01/04/2020
 */
public class MusicPlayer implements Runnable {

    private static String musicFile = "/audio/music.wav";

    private void playMusic() {
        try {
            URL url = getClass().getResource(musicFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.loop(-1);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
            clip.start();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void run() {
        playMusic();
    }
}
