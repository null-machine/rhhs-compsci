import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import java.util.Random;

/**
* A singleton that generates and plays sound effects from waveforms.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Sound {

  // singleton behaviour
  public static Sound instance = new Sound();

  private final float sampleRate = 32000;
  private final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, true);

  private byte[] genWave(double time, Waveform shape, Waveform freq, Waveform amp) {
    // flexible soundwave generation
    byte[] wave = new byte[(int)(time * sampleRate)];
    for (int i = 0; i < wave.length; ++i) {
      double totalPercent = (double)i / (double)wave.length;
      double cyclePercent = (i * freq.eval(totalPercent)) % sampleRate / sampleRate;
      wave[i] = (byte)((shape.eval(cyclePercent) * 2.0 - 1.0) * amp.eval(totalPercent) * Byte.MAX_VALUE);
    }
    return wave;
  }

  /**
  * Generates and plays a sound effect in a seperate thread.
  * All sound effect volumes are set by an attack-delay
  * musical envelope to make them sound more natural.
  * @param time The duration of the sound effect.
  * @param shape The waveform of the sound effect. Related to timbre.
  * @param freq The frequency of the sound effect. Supports
  * frequency modulation. Related to pitch.
  */
  public void play(double time, Waveform shape, Waveform freq) {
    byte[] wave = genWave(
      time,
      shape,
      freq,
      percent -> (percent < 0.5) ? percent * 2.0 : 1.0 - (percent - 0.5) * 2.0
    );
    Thread speaker = new Thread(new Speaker(wave));
    speaker.start();
  }

  private class Speaker implements Runnable {

    private byte[] wave;

    @Override
    public void run() {
      try {
        SourceDataLine line = AudioSystem.getSourceDataLine(format);
        line.open(format);
        line.start();
        line.write(wave, 0, wave.length);
        line.drain();
        line.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private Speaker(byte[] wave) {
      this.wave = wave;
    }
  }
}
