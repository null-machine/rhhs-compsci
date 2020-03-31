// Author : CATHERINE

public class Font {
  
  public static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ    " + "0123456789-.!?/%$\\=*+,;:()&#\"' ";
  
  /*  Gets string width
   *  @author: Catherine
   * @param: s- string, is the string
   *  @return the string width
   */ 
  public static int getStringWidth(String s) {
    return s.length() * 12;
  }// end of getStringWidth();
  
  /*  Draws message on the screen 
   *  @author: Catherine
   * @param screen, the screen
   * @param msg the message wanting to be print
   * @param x the x location
   * @param y the y location
   */ 
  public static void draw(Screen screen, String msg, int x, int y) {
    msg = msg.toUpperCase();
    int length = msg.length();
    for (int i = 0; i < length; i++) {
      int c = letters.indexOf(msg.charAt(i));
      if (c < 0) continue;
      screen.render(Art.font[c % 30][c / 30], x, y);
      x += 12;
    }// end of for loop
  }// end of draw()
  
}// end of font class
