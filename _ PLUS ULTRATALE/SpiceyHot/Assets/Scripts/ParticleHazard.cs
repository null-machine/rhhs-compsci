using UnityEngine;

[RequireComponent(typeof(ParticleSystem))]
public class ParticleHazard : Hazard {
	
	protected override void Awake() {
		base.Awake();
	}
	
	private void OnParticleCollision() {
		Debug.Log("hit");
		Damage();
	}
}
