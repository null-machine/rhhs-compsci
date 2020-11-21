using UnityEngine;

public abstract class Hazard : MonoBehaviour {
	
	[SerializeField]
	private int damage;
	private Player player;
	
	protected virtual void Awake() {
		player = GameObject.FindWithTag("Player").GetComponent<Player>();
	}
	
	protected void Damage() {
		player.Health -= damage;
	}
}
