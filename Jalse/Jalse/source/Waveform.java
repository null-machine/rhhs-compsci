/**
* A customizable waveform used as parameters for sound wave generation.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public interface Waveform {

  /**
  * Evaluates the waveform at a certain percent.
  * @param percent The percent of the waveform to evaluate.
  * @return The waveform value at that percent.
  */
  abstract public double eval(double percent);
}
