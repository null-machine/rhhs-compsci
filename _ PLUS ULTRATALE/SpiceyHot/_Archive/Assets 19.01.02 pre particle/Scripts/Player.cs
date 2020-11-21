using UnityEngine;

public class Player : Entity {
	
	private Animator anim;
	
	public Rigidbody2D Rb { get { return rb; } }
	
	[SerializeField]
	private int maxHealth;
	public int MaxHealth { get { return maxHealth; } }
	
	private int health;
	
	public bool AddHealth(float val) {
		bool hurt = false;
		if(!invul && health > val) {
			hurt = true;
			invul = true;
			anim.SetBool("invul", invul);
			Invoke("ResetInvul", invulTime);
		}
		health = (int)Mathf.Clamp(val, 0f, maxHealth);
		if(health == 0) {
			Debug.Log("dead");
		}
		return hurt;
	}
	
	[SerializeField]
	private float invulTime;
	private bool invul;
	private void ResetInvul() {
		invul = false;
		anim.SetBool("invul", invul);
	}
	
	protected override void Awake() {
		base.Awake();
		anim = GetComponent<Animator>();
		health = maxHealth;
	}
	
	private void FixedUpdate() {
		//Debug.Log(new Vector2(Input.GetAxisRaw("Horizontal"), Input.GetAxisRaw("Vertical")));
		rb.velocity = new Vector2(Input.GetAxisRaw("Horizontal"), Input.GetAxisRaw("Vertical")) * speed;
	}
	
	private void OnDisable() {
		sr.color = new Color(1f, 0f, 0f);
	}
}
