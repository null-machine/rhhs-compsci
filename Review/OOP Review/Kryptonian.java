/* Kryptonian.java
 * .
 */

public class Kryptonian extends Superhero implements CanFly {
	
	Kryptonian(int health) {
		super(health);
		this.originPlanet = "Krypton";
	}
	
	public void fly() {
		System.out.println("oh btw i can fly too");
	}
	
	protected void act() {
		super.act();
		fly();
	}
	
}
