using UnityEngine;

public class BlueIcicle : Icicle {
	
	private void OnTriggerStay2D() {
		if(BlueDamage()) Destroy(gameObject);
	}
}
