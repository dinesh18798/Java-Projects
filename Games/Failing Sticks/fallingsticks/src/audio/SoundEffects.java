package audio;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Created by Dinesh on 01/04/2020
 */
public class SoundEffects {

    private static String clickFile = "/audio/click.wav";
    private static String shatterFile = "/audio/shatter.wav";

    private Clip clickClip;
    private Clip shatterClip;

    public SoundEffects() {
        createClickClip();
        createShatterClip();
    }

    private void createClickClip() {
        try {
            URL url = getClass().getResource(clickFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            clickClip = (Clip) AudioSystem.getLine(info);
            clickClip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clickClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void createShatterClip() {
        try {
            URL url = getClass().getResource(shatterFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            shatterClip = (Clip) AudioSystem.getLine(info);
            shatterClip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) shatterClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void playClickEffect() {
        try {
            if (clickClip != null) {
                clickClip.setFramePosition(0);
                clickClip.start();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void playShatterEffect() {
        try {
            if (shatterClip != null) {
                shatterClip.setFramePosition(0);
                shatterClip.start();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
