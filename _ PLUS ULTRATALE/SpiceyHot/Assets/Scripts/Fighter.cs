using UnityEngine;

public class Fighter : MonoBehaviour {
	
	private Animator anim;
	
    [SerializeField]
	protected int maxHealth;
	protected int health;
	public int Health {
		get { return health; }
		set {
			if(!IsInvoking("ResetInvul")) {
				health = (int)Mathf.Clamp(value, 0f, maxHealth);
				if(health == 0) {
					Debug.Log("dead");
				}
				SetInvul();
				Invoke("ResetInvul", invulTime);
			}
		}
	}
	
	[SerializeField]
	private float invulTime;
	private int startLayer;
	private void SetInvul() {
		anim.SetBool("invul", true);
		gameObject.layer = LayerMask.NameToLayer("Invul");
	}
	private void ResetInvul() {
		anim.SetBool("invul", false);
		gameObject.layer = startLayer;
	}
	
	protected virtual void Awake() {
		anim = GetComponent<Animator>();
		health = maxHealth;
		startLayer = gameObject.layer;
	}
}
