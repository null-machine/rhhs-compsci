using UnityEngine;

//[RequireComponent(typeof(Rigidbody2D), typeof(Collider2D), typeof(SpriteRenderer))]
public abstract class Entity : MonoBehaviour {
	
	protected Rigidbody2D rb;
	protected Collider2D col;
	protected SpriteRenderer sr;
	
	[SerializeField]
	protected float speed;
	
	protected virtual void Awake() {
		rb = GetComponent<Rigidbody2D>();
		col = GetComponent<Collider2D>();
		sr = GetComponent<SpriteRenderer>();
	}
}
