/* Being.java
 * .
 */

import java.util.ArrayList;

public class Being {
	
	protected int health;
	protected String originPlanet;
	
	Being(int health) {
		this.health = health;
	}
	
	protected void act() {
		System.out.println("i be");
	}
	
}
