/* Being.java
 * .
 */

public class Superhero extends Being {
	
	Superhero(int health) {
		super(health);
		this.originPlanet = "Krypton";
	}
	
	protected void act() {
		super.act();
		System.out.println("jokes im a superhero");
	}
	
}
