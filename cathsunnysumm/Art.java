// Author : SUNNY
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Art {
  
  public static final Bitmap[][] sprites = cut("/icon0.png", 32, 32);
  public static final Bitmap black = load("/black.png");
  public static final Bitmap pk = load("/pk.png");
  public static final Bitmap[][] font = cut("/font.png", 16, 16);
  
  private static Bitmap[][] cut(String string, int w, int h) {
    return cut(string, w, h, 0, 0);
  }
  
    /* finds location of file and cuts a bitmap image
   * @param string location of file
   * @param w width of picture
   * @param h height of picture
   * @param bx x coord of picture
   * @param by y coord of picture
   * return 0= sucess, 1= unsucessful
   */
  private static Bitmap[][] cut(String string, int w, int h, int bx, int by) {
    try {
      BufferedImage bi = ImageIO.read(GameLauncher.class.getResource(string));
      
      int xTiles = (bi.getWidth() - bx) / w;
      int yTiles = (bi.getHeight() - by) / h;
      
      Bitmap[][] result = new Bitmap[xTiles][yTiles];
      
      for (int x = 0; x < xTiles; x++) {
        for (int y = 0; y < yTiles; y++) {
          result[x][y] = new Bitmap(w, h);
          bi.getRGB(bx + x * w, by + y * h, w, h, result[x][y].pixels, 0, w);
        }
      }
      
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /* gets color from the bitmap tile array and returns it
   * @param tiles get a specific tile on the level
   * return the 2 d array of colors
   */
  private static int[][] getColors(Bitmap[][] tiles) {
    int[][] result = new int[tiles.length][tiles[0].length];
    for (int y = 0; y < tiles[0].length; y++) {
      for (int x = 0; x < tiles.length; x++) {
        result[x][y] = getColor(tiles[x][y]);
      }
    }
    return result;
  }
  
  /* gets color from the bitmap array and returns it
   * @param bitmap get a specific tile on the level
   * get 1 specific color from the indicated location on window
   */
  private static int getColor(Bitmap bitmap) {
    int r = 0;
    int g = 0;
    int b = 0;
    for (int i = 0; i < bitmap.pixels.length; i++) {
      int col = bitmap.pixels[i];
      r += (col >> 16) & 0xff;
      g += (col >> 8) & 0xff;
      b += (col) & 0xff;
    }
    
    r /= bitmap.pixels.length;
    g /= bitmap.pixels.length;
    b /= bitmap.pixels.length;
    
    return 0xff000000 | r << 16 | g << 8 | b;
  }
  /* load the image we have based on location
   * @param string location of the image
   * return the bitmap object 
   */
  private static Bitmap load(String string) {
    try {
      BufferedImage bufferedImage = ImageIO.read(GameLauncher.class.getResource(string));
      
      int w = bufferedImage.getWidth();//find width of image
      int h = bufferedImage.getHeight();//find height of image
      
      Bitmap result = new Bitmap(w, h);
      bufferedImage.getRGB(0, 0, w, h, result.pixels, 0, w);//turn all the rgb data into an array of integers
      
      
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  /* cut the image we have based on location
   * @param string location of the image
   * @param h the height of the image
   * return the bitmap object 
   */
  private static Bitmap[] cut(String string, int h) {
    try {
      BufferedImage bi = ImageIO.read(GameLauncher.class.getResource(string));
      
      int yTiles = bi.getHeight() / h;
      int w = bi.getWidth();
      
      Bitmap[] result = new Bitmap[yTiles];
      
      for (int y = 0; y < yTiles; y++) {
        result[y] = new Bitmap(w, h);
        bi.getRGB(0, y * h, w, h, result[y].pixels, 0, w);
      }
      
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
}
