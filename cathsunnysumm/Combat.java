// Author : SUNNY 

public class Combat {
  
  
  /* calculate theortical damage done to entity
   * 
   * 
   */
  
  public static int calculateDamage(Player player, Entity entity) {
    if(!entity.hostile) {//if enemy is not hostile, no damage will be dealth
      return 0;
    }
    int playerHealth = player.getProperty("stat.hp");
    int playerDefense = player.getProperty("stat.defense");
    int playerAttack = player.getProperty("stat.attack");
    
    int enemyHp = entity.hp;
    int enemyAttack = entity.attack;
    int enemyDefense = entity.defense;
    
    int hypotheticalDmgTakenByPlayer = 0;
    
    int turn = 0;
    boolean canend = false;
    while(!canend) {
      if(turn == 0) {//turn = 0 means it is the players turn
        
        int dmg = playerAttack - enemyDefense;//see how much damage player can deal
        if(dmg <= 0) {//if defense is too great, 
          return -1;//combat cannot proceed, return -1
        }
        else {
          enemyHp -= dmg;//if the enemy lives, move on to enemie's turn
          turn = 1;//turn = 1 is the enemy turn
          break;
        }
      }
      else if (turn == 1){
        int enemyDamage = enemyAttack - playerDefense;//check the damage the enemy can deal to player
        if(enemyDamage <= 0) {//if the enemy cannot deal damage, return 0 as calculated damage
          return 0;
        }
        else {
          playerHealth -= enemyDamage;//if it can deal damage, add to damage taken by player
          hypotheticalDmgTakenByPlayer += enemyDamage;
          turn = 0;//return to players turn
          break;
        }
      }
      if(hypotheticalDmgTakenByPlayer >= player.getProperty("stat.hp")) {
        return -1;
      }
      if(enemyHp <= 0 || playerHealth <= 0) {
        canend = true;//if either player or enemy dies, stop loop
      }
    }
    return hypotheticalDmgTakenByPlayer;
  }
  
}
