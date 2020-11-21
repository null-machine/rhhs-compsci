using UnityEngine;
using System.Collections;

public class MainBox : MonoBehaviour {
	
	[SerializeField]
	private Player player;
	
	private SpriteRenderer sr;
	
	private EdgeCollider2D col;
	private Vector2[] colDimen;
	
	[SerializeField]
	private float colMargin;
	
	[SerializeField]
	private float transitionSpeed;
	
	public IEnumerator Transition(float width, float height) {
		//Debug.Log("transitioning to " + width + " " + height);
		do {
			sr.size = Vector2.MoveTowards(sr.size, new Vector2(width, height), transitionSpeed);
			//sr.size = new Vector2(Mathf.Clamp(sr.size.x, 0f, width), Mathf.Clamp(sr.size.y, 0f, height));
			//Debug.Log(sr.size + " " + new Vector2(width, height));
			yield return new WaitForFixedUpdate();
		} while(sr.size != new Vector2(width, height));
		UpdateCol();
		player.transform.position = transform.position + new Vector3(0f, sr.size.y / 2f, 0f);
		//Debug.Log("transitioned to " + width + " " + height);
	}
	
	private void UpdateCol() {
		float xSize = sr.size.x / 2f - colMargin;
		float ySize = sr.size.y - colMargin;
		colDimen[0] = new Vector2(-xSize, colMargin);
		colDimen[1] = new Vector2(-xSize, ySize);
		colDimen[2] = new Vector2(xSize, ySize);
		colDimen[3] = new Vector2(xSize, colMargin);
		colDimen[4] = new Vector2(-xSize, colMargin);
		col.points = colDimen;
	}
	
	private void Awake() {
		sr = GetComponent<SpriteRenderer>();
		col = GetComponent<EdgeCollider2D>();
		colDimen = new Vector2[5];
	}
}
