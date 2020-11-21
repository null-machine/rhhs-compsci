using UnityEngine;
using System.Collections;

//[RequireComponent(typeof(Collider2D))]
public class ColliderHazard : Hazard {
	
	[SerializeField]
	private float growTime;
	
	private IEnumerator Grow() {
		float startTime = Time.time;
		Vector3 startScale = transform.localScale;
		transform.localScale = Vector3.zero;
		
		Component[] emitters = GetComponentsInChildren<ParticleSystem>();
		foreach(ParticleSystem ps in emitters) ps.Play();
		
		for(float i = Time.fixedDeltaTime; i < growTime; i += Time.fixedDeltaTime) {
			transform.localScale += startScale * (Time.fixedDeltaTime / growTime);
			yield return new WaitForFixedUpdate();
		}
		
		
	}
	
	
	protected override void Awake() {
		base.Awake();
		StartCoroutine(Grow());
	}
	
	private void OnTriggerEnter2D() {
		Damage();
	}
}
