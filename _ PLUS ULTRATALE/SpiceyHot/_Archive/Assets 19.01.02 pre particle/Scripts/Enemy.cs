using UnityEngine;
using System.Collections;

public class Enemy : MonoBehaviour {
	
	[SerializeField]
	private MainBox mainBox;
	[SerializeField]
	private Buttons buttons;
	private GameObject player;
	
	[SerializeField]
	private GameObject icicleClump;
	[SerializeField]
	private GameObject particleAttack;
	
	private void DeleteHazards() {
		GameObject[] hazards = GameObject.FindGameObjectsWithTag("Hazard");
		foreach(GameObject hazard in hazards) {
			Destroy(hazard);
		}
	}
	
	public IEnumerator Attack(int turn) {
		Debug.Log("enemy attack called: " + turn);
		switch(turn) {
			case 1:
				yield return StartCoroutine(ParticleAttack());
				break;
			default:
				yield return StartCoroutine(IcicleBurst());
				break;
		}
		DeleteHazards();
		StartCoroutine(buttons.PlayerChoose());
	}
	
	private IEnumerator IcicleBurst() {
		//Debug.Log("icicle burst called");
		yield return StartCoroutine(mainBox.Transition(3f, 2f));
		player.SetActive(true);
		Instantiate(icicleClump, new Vector3(-0.73f, 0.51f, 0f), Quaternion.Euler(0f, 0f, 180f));
		Instantiate(icicleClump, new Vector3(1.51f, -0.13f, 0f), Quaternion.Euler(0f, 0f, 90f));
		Instantiate(icicleClump, new Vector3(-0.17f, -1.53f, 0f), Quaternion.identity);
		yield return new WaitForSeconds(8f);
		//Debug.Log("icicle burst finished");
	}
	
	private IEnumerator ParticleAttack() {
		yield return StartCoroutine(mainBox.Transition(2.6f, 1.3f));
		player.SetActive(true); // TODO sep transitions, on atk trans activate player
		Instantiate(particleAttack, Vector3.zero, Quaternion.identity);
		yield return new WaitForSeconds(8f);
	}
	
	private void Awake() {
		player = GameObject.FindWithTag("Player");
	}
}
