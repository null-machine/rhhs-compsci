using UnityEngine;
using System.Collections;

public class IcicleClump : Hazard {
	
	private GameObject player;
	[SerializeField]
	private GameObject whiteIcicle;
	[SerializeField]
	private GameObject blueIcicle;
	[SerializeField]
	private float numWhite;
	[SerializeField]
	private float numBlue;
	[SerializeField]
	private float waveRate;
	[SerializeField]
	private float waveStagger;
	
	private IEnumerator SpawnIcicle(GameObject icicle, float angle) {
		yield return new WaitForSeconds(Random.Range(0f, waveStagger));
		icicle = Instantiate(icicle, transform);
		icicle.transform.rotation = Quaternion.AngleAxis(angle, Vector3.forward);
	}
	
	private void SpawnAimed() {
		Vector3 targetDir = player.transform.position - transform.position;
		float angle = Mathf.Atan2(targetDir.y, targetDir.x) * Mathf.Rad2Deg - 90f;
		GameObject icicle;
		if(Random.value > 0.2f) icicle = whiteIcicle;
		else icicle = blueIcicle;
		StartCoroutine(SpawnIcicle(icicle, angle));
	}
	
	private void SpawnBlind(GameObject icicle) {
		float angle = transform.rotation.eulerAngles.z + Random.Range(-90f, 90f);
		StartCoroutine(SpawnIcicle(icicle, angle));
	}
	
	private void SpawnWave() {
		for(int i = 0; i < numWhite; ++i) SpawnBlind(whiteIcicle);
		for(int i = 0; i < numBlue; ++i) SpawnBlind(blueIcicle);
		SpawnAimed();
	}
	
	private IEnumerator Init() {
		yield return StartCoroutine(Grow());
		while(gameObject) {
			SpawnWave();
			yield return new WaitForSeconds(waveRate + waveStagger);
		}
	}
	
	protected override void Awake() {
		base.Awake();
		player = GameObject.FindWithTag("Player");
		StartCoroutine(Init());
	}
	
	private void OnTriggerEnter2D() {
		if(WhiteDamage()) Destroy(gameObject);
	}
}
