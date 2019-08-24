import java.awt.Color;

/**
* A model for a well-rounded racer.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class Balance extends Model {

  /**
  * Constructs a balanced racer model.
  */
  public Balance() {
    speed = 1.0;
    control = 0.04;
    color = new Color(0, 248, 104);
    size = 56;
    fragility = 700;
    calcDrag();
    stats[0] = "***";
    stats[1] = "***";
    stats[2] = "**";
  }

  @Override
  public void soundCollide() {
    double pitchMod = randMod(200);
    Sound.instance.play(
      0.3, // 0.3 seconds
      percent -> (Math.round(percent) + 1.0) / 2.5 + randMod(0.3), // garbled square waveform with lower amplitude
      percent -> 300.0 - 20.0 * percent + pitchMod // dropping pitch
    );
  }

  @Override
  public void soundWin() {
    double pitchMod = randMod(120);
    Sound.instance.play(
      2.0, // two seconds
      percent -> (Math.round(percent) + 1.0) / 2.5, // square waveform
      percent -> 220.0 + percent * 180.0 + pitchMod // growing pitch
    );
  }

  @Override
  public void soundSelect() {
    double pitchMod = randMod(120);
    Sound.instance.play(
      0.5, // half a second
      percent -> (Math.round(percent) + 1.0) / 2.5, // square waveform
      percent -> 220.0 + percent * 180.0 + pitchMod // growing pitch
    );
  }
}
