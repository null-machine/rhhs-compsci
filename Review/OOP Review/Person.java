/* Being.java
 * .
 */

public class Person extends Being {
	
	Person(int health) {
		super(health);
		this.originPlanet = "Earth";
	}
	
	protected void act() {
		super.act();
		System.out.println("oh im also a person");
	}
}
