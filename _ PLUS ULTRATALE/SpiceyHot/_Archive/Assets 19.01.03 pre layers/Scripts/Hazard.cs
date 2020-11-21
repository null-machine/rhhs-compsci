using UnityEngine;

public abstract class Hazard : MonoBehaviour {
	
	private enum DamageType : int {WHITE, BLUE, ORANGE};
	[SerializeField]
	private DamageType damageType;
	
	protected delegate void HurtPlayer();
	protected HurtPlayer hurtPlayer;
	
	[SerializeField]
	private int damage;
	private Player player;
	
	protected virtual void Awake() {
		player = GameObject.FindWithTag("Player").GetComponent<Player>();
		switch(damageType) {
			case DamageType.WHITE: 
				hurtPlayer = WhiteDamage;
				break;
			case DamageType.BLUE:
				hurtPlayer = BlueDamage;
				break;
			case DamageType.ORANGE:
				hurtPlayer = OrangeDamage;
				break;
			default:
				break;
		}
	}
	
	protected void WhiteDamage() {
		player.Health -= damage;
	}
	
	protected void BlueDamage() {
		if(player.Moving) WhiteDamage();
	}
	
	protected void OrangeDamage() {
		if(!player.Moving) WhiteDamage();
	}
}
