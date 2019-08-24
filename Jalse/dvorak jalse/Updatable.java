
/**
* This interface allows the classes that implement it to be able to
* recieve updates from the game master.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public interface Updatable {

  /**
  * Updates this so that it assumes a state
  * an amount of time in the future.
  * @param deltaTime The amount of time in milliseconds.
  */
  abstract public void update(long deltaTime);
}
