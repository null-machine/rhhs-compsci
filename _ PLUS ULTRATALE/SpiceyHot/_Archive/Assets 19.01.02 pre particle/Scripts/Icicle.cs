using UnityEngine;
using System.Collections;

public abstract class Icicle : Hazard {
	
	[SerializeField]
	protected float pauseTime;
	/*
	protected void Propel() {
		rb.velocity = transform.up * speed;
	}
	
	protected override void Awake() {
		base.Awake();
		StartCoroutine(Grow());
		Invoke("Propel", growTime + pauseTime);
	}
	*/
}
