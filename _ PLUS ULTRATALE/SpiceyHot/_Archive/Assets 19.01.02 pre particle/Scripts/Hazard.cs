using UnityEngine;
using System.Collections;

public abstract class Hazard : Entity {
	
	[SerializeField]
	protected int damage;
	[SerializeField]
	protected float growTime;
	
	private Player playerScript;
	private Rigidbody2D playerRb;
	
	protected virtual void Awake() {
		GameObject player = GameObject.FindWithTag("Player");
		playerScript = player.GetComponent<Player>();
		playerRb = player.GetComponent<Rigidbody2D>();
	}
	
	protected IEnumerator Grow() {
		float startTime = Time.time;
		Vector3 startScale = transform.localScale;
		transform.localScale = Vector3.zero;
		
		for(float i = Time.fixedDeltaTime; i < growTime; i += Time.fixedDeltaTime) {
			transform.localScale += startScale * (Time.fixedDeltaTime / growTime);
			yield return new WaitForFixedUpdate();
		}
	}
	
	protected bool WhiteDamage() {
		return playerScript.AddHealth(-damage);
	}
	
	protected bool BlueDamage() {
		if(playerRb.velocity != Vector2.zero) return WhiteDamage();
		else return false;
	}
	
	protected bool OrangeDamage() {
		if(playerRb.velocity == Vector2.zero) return WhiteDamage();
		else return false;
	}
}
