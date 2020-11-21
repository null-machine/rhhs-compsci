using UnityEngine;

[RequireComponent(typeof(ParticleSystem))]
public class Emitter : Hazard {
	
	private ParticleSystem ps;
	
	protected override void Awake() {
		base.Awake();
		ps = GetComponent<ParticleSystem>();
	}
	
	private void OnParticleCollision() {
		WhiteDamage();
	}
}
