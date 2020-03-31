// Author : SUNNY 
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DynamicsLoader {
  
  public static void init(GameLauncher game, InputHandler input) {
    loadTiles();
    
    loadEntities();
    loadLevels(game, input);
  }
  
  
  
  private static void loadLevels(GameLauncher game, InputHandler input) {
    
    XMLFile levelDescrip = new XMLFile("/level.xml");//
    Document doc = levelDescrip.asDocument();
    //Find the levels node, then the define node, and then read the nodes from the xml file
    NodeList pxlDefine = ((Element) ((Element) doc.getElementsByTagName("levels").item(0)).getElementsByTagName("define").item(0)).getElementsByTagName("pixel");
    List<Color> pxlcol = new ArrayList<Color>();//store the color data into this array
    List<String> bound = new ArrayList<String>();//store the bind data here
    BufferedImage lv = null;//lv means level
    try {
      lv = ImageIO.read(GameLauncher.class.getResourceAsStream("/levels.png"));//store our png into a bufferedimage
    } catch (IOException e) {
      e.printStackTrace();
    }
    int xt = lv.getWidth() / 12;//each level will be 12 tiles wide, split it here
    int yt = lv.getHeight() / 13;//each level will be 13 tiles height, split it here
    BufferedImage[][] levels = new BufferedImage[xt][yt];//two dimensional array storing all the levels
    //we put all the levels in a one dimensional array
    Level[] lvs = new Level[xt * yt];//lvs means levels. we do xt*yt to find the total amount of levels we have
    
    int count = 0;
    for(int i = 0; i < levels.length; i++) {
      for(int j = 0; j < levels[i].length; j++) {
        levels[i][j] = lv.getSubimage(i * 12, j * 13, 12, 13);//get a subimage of each level
        lvs[count] = new Level();//make a new level object for each subimage
        lvs[count].floor = count + 1;//denote which floor this level is on
        count++;
      }
    }
    for(int i = 0; i < pxlDefine.getLength(); i++) {//loop through all the nodes
      Element e = (Element) pxlDefine.item(i);//cast the node into an element so we can read it
      String[] rgb = e.getAttribute("rgb").split(",");//find it's RGB and split it in 3 values
      if(rgb.length != 3) {
        throw new RuntimeException("RGB Length is invalid in XML file!");
      }
      //converting string value to integer value 
      int[] rgbIntValue = new int[3];
      rgbIntValue[0] = Integer.parseInt(rgb[0]);
      rgbIntValue[1] = Integer.parseInt(rgb[1]);
      rgbIntValue[2] = Integer.parseInt(rgb[2]);
      
      //create a color object that has the color data of that pixel
      Color col = new Color(rgbIntValue[0], rgbIntValue[1], rgbIntValue[2]);
      pxlcol.add(col);//add color data into pxlcol array
      bound.add(e.getAttribute("bind"));//add bind data into bound array
    }
    
    count = 0;
    
    
    for(int a = 0; a < levels.length; a++) {//goes through each row of tile levels
      for(int b = 0; b < levels[a].length; b++) {//goes through each column of tile levels
        int w = levels[a][b].getWidth();
        int h = levels[a][b].getHeight();
        for(int i = 0; i < w; i++) {//within each tile level, get the color values and store it
          for(int j = 0; j < h; j++) {
            //will store rgb information into 1 integer
            int rgb = levels[a][b].getRGB(i, j);
            //bitshift by 16 to get pure red value
            int rr = (rgb >> 16) & 0xFF;
            //bitshift by 8
            int gg = (rgb >> 8) & 0xFF;
            int bb = rgb & 0xFF;
            
            //compare if the color we have on the png matches a color that we have assigned a tile to in the xml file
            for(int k = 0; k < pxlcol.size(); k++) {
              Color c = pxlcol.get(k);
              if(rr == c.getRed() && gg == c.getGreen() && bb == c.getBlue()) {
                //if it does, we add the tile id into a specific location in the tile of our level
                lvs[count].tiles[i][j] = Tile.tiles[Integer.parseInt(bound.get(k))].id;
              }
            }
          }
        }
        count++; //increase floor
      }
    }
    NodeList xmlLevels = ((Element) doc.getElementsByTagName("levels").item(0)).getElementsByTagName("level");//get each level node
    for(int i = 0; i < xmlLevels.getLength(); i++) {
      Element elementLevel = (Element) xmlLevels.item(i);//turn each level into an element
      int floor = Integer.parseInt(elementLevel.getAttribute("floor"));
      
      NodeList entities = elementLevel.getElementsByTagName("entity");//get all the entities on that level
      for(int j = 0; j < entities.getLength(); j++) {//loop through all the entities on that level
        
        Element entityElement = (Element) entities.item(j);
        String type = entityElement.getAttribute("type");
        
        //checks if the looped through entity is a player
        if(type.equalsIgnoreCase("Player")) {
          Player player = new Player(input, game, lvs[floor - 1]);
          player.setPos(Integer.parseInt(entityElement.getAttribute("x")), Integer.parseInt(entityElement.getAttribute("y")));
          lvs[floor - 1].addEntity(player);
          continue;
        }
        
        //check if the element is a stair
        if(type.equalsIgnoreCase("StairsUp") || type.equalsIgnoreCase("StairsDown")) {
          //create a new stairs object
          int dir = 0;
          if (type.equalsIgnoreCase("stairsup")){
            dir = 0;
          } else{
            dir = 1;
          }
          Stairs stairs = new Stairs(game, dir, Integer.parseInt(entityElement.getAttribute("tx")), Integer.parseInt(entityElement.getAttribute("ty")));
          stairs.setPos(Integer.parseInt(entityElement.getAttribute("x")), Integer.parseInt(entityElement.getAttribute("y")));
          lvs[floor - 1].addEntity(stairs);
          continue;
        }
        
        
        if(type.equalsIgnoreCase("npc")) {
          NPC npc = new NPC(entityElement.getAttribute("title"), entityElement.getAttribute("oninteract"), (entityElement.getAttribute("remove").equals("1")) ? true : false, Integer.parseInt(entityElement.getAttribute("sprite")));
          npc.setPos(Integer.parseInt(entityElement.getAttribute("x")), Integer.parseInt(entityElement.getAttribute("y")));
          lvs[floor - 1].addEntity(npc);
          continue;
        }
        Entity clonedEntity = Entity.newInstance(type);//cloning entity to be placed in levels
        
        clonedEntity.setPos(Integer.parseInt(entityElement.getAttribute("x")), Integer.parseInt(entityElement.getAttribute("y")));//set the entity on some position on the map
        lvs[floor - 1].addEntity(clonedEntity);//add the cloned entity onto that floor
      }
      
    }
    for(int i = 0; i < lvs.length; i++) {
      if(i <= 20) {
        Level.levels.put(lvs[i].floor, lvs[i]);
      }
      if(i >= 21 && i <= 51){
        Level.levels.put(-(i - 21), lvs[i]);
      }
      
    }
  }
  
  private static void loadEntities() {
    XMLFile entityFromFile = new XMLFile("/entities.xml");
    Document doc = entityFromFile.asDocument();
    NodeList entities = doc.getElementsByTagName("entities");//get root tag
    NodeList entityRegistered = ((Element) entities.item(0)).getElementsByTagName("entity");//grab every single entity node
    for(int i = 0; i < entityRegistered.getLength(); i++) {
      Element e = (Element) entityRegistered.item(i);
      //add the specific key of an entity from our xml file and add it too the hashmap
      Entity.entities.put(e.getAttribute("key"), new Entity(e.getAttribute("name"), e.getAttribute("sprite"),
                                                            //scrapped ideas of hp attack adn defense
                                                            Integer.parseInt(e.getAttribute("hp")),
                                                            Integer.parseInt(e.getAttribute("attack")),
                                                            Integer.parseInt(e.getAttribute("defense"))));
      
    }
  }
  
  private static void loadTiles() {
    XMLFile tdyn = new XMLFile("/tiles.xml");
    Document doc = tdyn.asDocument();
    NodeList tileNodes = doc.getElementsByTagName("tiles");
    for(int i = 0; i < tileNodes.getLength(); i++) {
      Node n = tileNodes.item(i);
      NodeList tiles = ((Element) n).getElementsByTagName("tile");
      for(int j = 0; j < tiles.getLength(); j++) {
        Element e = (Element) tiles.item(j);
        boolean newIsSolid = false;
        if (e.getAttribute("solid").equals("1")){
          newIsSolid = true;
        } else{
          newIsSolid=false;
        }
        new Tile(Integer.parseInt(e.getAttribute("id")),e.getAttribute("name"), newIsSolid, e.getAttribute("frames"));
      }
    }
  }
}
