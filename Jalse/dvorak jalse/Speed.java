import java.awt.Color;

/**
* A model for a fast but challenging racer.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Speed extends Model {

  /**
  * Constructs a speedy racer model.
  */
  public Speed() {
    speed = 1.4;
    control = 0.03;
    color = new Color(249, 0, 96);
    size = 48;
    fragility = 1000;
    calcDrag();
    stats[0] = "*****";
    stats[1] = "*";
    stats[2] = "*";
  }

  private double triangleWave(double percent) {
    // triangular wave starting from 0.5 with range bounded from 0 to 1
    if (percent < 0.25) {
      return 0.5 + percent * 2.0;
    } else if (percent < 0.75) {
      return 1.0 - (percent - 0.25) * 2.0;
    } else {
      return (percent - 0.75) * 2.0;
    }
  }

  @Override
  public void soundCollide() {
    double pitchMod = randMod(60);
    Sound.instance.play(
      0.4, // 0.4 seconds
      percent -> triangleWave(percent) + randMod(0.5), // garbled triangular waveform
      percent -> 420.0 - 360.0 * percent / 2.0 + pitchMod // dropping pitch
    );
  }

  @Override
  public void soundWin() {
    double pitchMod = randMod(200);
    Sound.instance.play(
      2.0, // two seconds
      percent -> triangleWave(percent), // triangular waveform
      percent -> 240.0 + percent * 300.0 + pitchMod // growing pitch
    );
  }

  @Override
  public void soundSelect() {
    double pitchMod = randMod(200);
    Sound.instance.play(
      0.5, // half a second
      percent -> triangleWave(percent), // triangular waveform
      percent -> 240.0 + percent * 300.0 + pitchMod // growing pitch
    );
  }
}
