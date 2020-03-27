package GameController;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {
    public static void play(String path) {
        try {
            int EXTERNAL_BUFFER_SIZE = 524288;

            File soundFile = new File(path);
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine sourceDataLine;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(format);
            sourceDataLine.start();

            int nBytesRead = 0;
            byte[] data = new byte[EXTERNAL_BUFFER_SIZE];

            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(data, 0, data.length);
                if (nBytesRead >= 0) {
                    sourceDataLine.write(data, 0, nBytesRead);
                }
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}