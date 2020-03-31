//AUTHOR: CATHERINE  & SUNNY

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap {

 public BufferedImage image;
 
 public Screen(int w, int h) {
  super(w, h);
  image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);// creates no bufered image
  pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
 }// end of screen();

}
