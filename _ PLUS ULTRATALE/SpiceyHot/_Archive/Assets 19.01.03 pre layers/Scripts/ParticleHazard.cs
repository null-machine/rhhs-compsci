using UnityEngine;
using System.Collections.Generic;

[RequireComponent(typeof(ParticleSystem))]
public class ParticleHazard : Hazard {
	
	protected override void Awake() {
		base.Awake();
	}
	
	private void OnParticleCollision() {
		Debug.Log("hit player");
		hurtPlayer();
	}
}
