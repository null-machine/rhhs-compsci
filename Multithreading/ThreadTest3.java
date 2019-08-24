/* ThreadTest3 - Demonstrating creating a your class as runnable 
 * and instantiating it from its own main - But starting the thread once
 * the object has been created
 * 
 * Mangat
 */
class ThreadTest3 implements Runnable {  
  
  //***
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
   

 //***** 
  public void go() {
    Thread threadB = new Thread(this);
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
  //***
    public static void main(String[] args) { 
      new ThreadTest3().go();
    }
  
  
}
