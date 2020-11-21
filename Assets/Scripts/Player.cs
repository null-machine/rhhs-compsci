using UnityEngine;

public class Player : Fighter {
	
	[SerializeField]
	private float speed;
	
	private Rigidbody2D rb;
	private SpriteRenderer sr;
	public bool Moving {
		get {
			if(rb.velocity == Vector2.zero) return false;
			else return true;
		}
	}
	
	protected override void Awake() {
		base.Awake();
		rb = GetComponent<Rigidbody2D>();
		sr = GetComponent<SpriteRenderer>();
	}
	
	private void FixedUpdate() {
		rb.velocity = new Vector2(Input.GetAxisRaw("Horizontal"), Input.GetAxisRaw("Vertical")) * speed;
		if(gameObject.layer != LayerMask.NameToLayer("Invul")) {
		if(rb.velocity == Vector2.zero) gameObject.layer = LayerMask.NameToLayer("StoppedPlayer");
		else gameObject.layer = LayerMask.NameToLayer("MovingPlayer");
		}
	}
	
	private void OnDisable() {
		sr.color = new Color(1f, 0f, 0f);
	}
}
