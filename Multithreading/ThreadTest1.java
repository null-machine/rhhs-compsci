/* ThreadTest1 - Demonstrating creating a runnable class and starting in
 * in main
 * Mangat
 */


class ThreadTest {
  public static void main(String[] args) { 
 
    Thread threadB = new Thread(new myRunnable());
    threadB.start();
    
   for(int i=0;i<100;i++) {
      
      System.out.println("Thread A is Running!");
      
      try{
        Thread.sleep(100);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
      
    
    
  }
}


/************ MyRunnable class
  * 
*/

class myRunnable implements Runnable { 

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
}