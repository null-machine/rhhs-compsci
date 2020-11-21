using UnityEngine;

public class WhiteIcicle : Icicle {
	
	private void OnTriggerStay2D() {
		if(WhiteDamage()) Destroy(gameObject);
	}
}
