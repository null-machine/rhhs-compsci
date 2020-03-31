
//AUTHOR: SUNNY

public class NPC extends Entity {
  
  public String title;
  public String[] text;
  public boolean disappear;
  public int type;
  
    /*create the NPC
     *@param title the title of the npc
     *@param text the the location of 
     *@param disappear whether or not the npc disappears after talking to them 
     *@param type the type of npc
     */
  public NPC(String title, String text, boolean disappear, int type) {
    super("", "", 1, 0, 1000000);
    this.title = title;
    this.text = text.split("#");
    hostile = false;
    this.disappear = disappear;
    this.type = type;
    
    
    
    if (type == 0){
      frames = new Bitmap[] { Art.sprites[0][12], Art.sprites[1][12] };
      
      
    } else if(type == 1){
      frames = new Bitmap[] { Art.sprites[0][13], Art.sprites[1][13] };
      
    } else if (type == 2){
      frames = new Bitmap[] { Art.sprites[0][14], Art.sprites[1][14] };
      
    } else if (type == 3){
      frames = new Bitmap[] { Art.sprites[0][15], Art.sprites[1][15] };
      
    } else if (type >= 4){
      frames = new Bitmap[] { Art.sprites[2][12], Art.sprites[3][12] };
      
    }
    
  }
  
  /*let npc interact with the player
     *@param player the player object we are interacting with
     */
  public void interact(Player player) {
    UI.npc(this, player);
    player.move(player.invertDir(player.playerFacingDirection),1);
  }
  
  
}
