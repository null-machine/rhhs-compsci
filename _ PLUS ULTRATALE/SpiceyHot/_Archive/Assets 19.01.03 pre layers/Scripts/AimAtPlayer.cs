using UnityEngine;

public class AimAtPlayer : MonoBehaviour {
    
    [SerializeField]
    private Transform t;
    private GameObject player;
    
    private void Awake() {
		player = GameObject.FindWithTag("Player");
	}
    
    private void FixedUpdate() {
		Vector2 dir = (Vector2)(player.transform.position - t.position);
		float angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg - 90f;
		t.rotation = Quaternion.AngleAxis(angle, Vector3.forward);
	} 
}
