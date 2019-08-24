import java.awt.Color;

/**
* A model for a safe but slow racer.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Control extends Model {

  /**
  * Constructs an easy-to-control racer model.
  */
  public Control() {
    speed = 0.8;
    control = 0.06;
    color = new Color(158, 0, 248);
    size = 64;
    fragility = 200;
    calcDrag();
    stats[0] = "*";
    stats[1] = "****";
    stats[2] = "***";
  }

  @Override
  public void soundCollide() {
    double pitchMod = randMod(100);
    Sound.instance.play(
      0.5, // half a second
      percent -> Math.sin(percent) / 2.0 + 0.5 + randMod(0.6), // garbled sine waveform
      percent -> 600.0 - 500.0 * percent / 2.0 + pitchMod // dropping pitch
    );
  }

  @Override
  public void soundWin() {
    double pitchMod = randMod(180);
    Sound.instance.play(
      2.0, // two seconds
      percent -> Math.sin(percent) / 2.0 + 0.5, // sine waveform
      percent -> 360.0 + percent * 200.0 + pitchMod // growing pitch
    );
  }

  @Override
  public void soundSelect() {
    double pitchMod = randMod(180);
    Sound.instance.play(
      0.5, // half a second
      percent -> Math.sin(percent) / 2.0 + 0.5, // sine waveform
      percent -> 360.0 + percent * 200.0 + pitchMod // growing pitch
    );
  }
}
