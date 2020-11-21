using UnityEngine;
using UnityEngine.EventSystems;
using System.Collections;

//[RequireComponent(typeof(EventSystem))]
public class Buttons : MonoBehaviour {
	
	[SerializeField]
	private MainBox mainBox;
	[SerializeField]
	private GameObject player;
	[SerializeField]
	private Enemy enemy;
	
	private GameObject lastSelected;
	private bool buttonPressed;
	private int turn;
	
	public void Fight() {
		//fight screen
		buttonPressed = true;
		//Debug.Log("fight button pressed " + buttonPressed);
	}
	
	public IEnumerator PlayerChoose() {
		player.SetActive(false);
		yield return StartCoroutine(mainBox.Transition(5.76f, 1.3f));
		EventSystem.current.SetSelectedGameObject(lastSelected);
		
		buttonPressed = false;
		yield return new WaitUntil(() => buttonPressed == true);
		buttonPressed = false;
		
		//Debug.Log("button press recieved" + lastSelected);
		
		lastSelected = EventSystem.current.currentSelectedGameObject;
		EventSystem.current.SetSelectedGameObject(null);
		StartCoroutine(enemy.Attack(++turn));
		
		//Debug.Log("enemy turn Awakeed");
	}
	
	private void Awake() {
		lastSelected = EventSystem.current.firstSelectedGameObject;
		StartCoroutine(PlayerChoose());
		turn = 0;
	}
	
	private void Update() {
		if(Input.GetMouseButtonDown(0) || Input.GetMouseButtonDown(1) || Input.GetMouseButtonDown(2)) {
			EventSystem.current.SetSelectedGameObject(lastSelected);
		}
	}
	
}
