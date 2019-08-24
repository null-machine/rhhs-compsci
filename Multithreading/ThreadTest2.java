/* ThreadTest2 - Demonstrating creating a your class as runnable 
 * and instantiating it from its own main
 * 
 * Mangat
 */

class ThreadTest2 implements Runnable {  
  
  
   public void run() { 
  
    for(int i=0;i<100;i++) {
      
      System.out.println("Thread B is Running!");
      
      try{
        Thread.sleep(100);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
   }
  
  
  public static void main(String[] args) { 
 
    Thread threadB = new Thread(new ThreadTest2());
    threadB.start();
    
   for(int i=0;i<100;i++) {
      
      System.out.println("Thread A is Running!");
      
      try{
        Thread.sleep(200);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
  }
}
