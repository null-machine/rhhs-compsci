/* ThreadTest6 - Demonstrating syncronization DEADLOCK
 * 
 * Note - A synchronization block allows you to synchronize a chunk of code
 * instead of a full method. You can even synchronize(this) to refer to the 
 * current instance 
 * Mangat
 */
class ThreadTest7 implements Runnable {  
  
  Object item1 = "item1";
  Object item2 = "item2";
 
   public void run() { 
  
    //for(int i = 0; i <10 ; i++) {

     if (Thread.currentThread().getName().equals("A")) {          //Thread A
       
       synchronized(item1) { 
         System.out.println("Thread A holding item 1 and waiting for 2");
         synchronized(item2) { 
           System.out.println("Thread A holding item 2");
         }
       }
       System.out.println("Thread A done.");
       
     }else if (Thread.currentThread().getName().equals("B")) {    //Thread B 
       synchronized(item2) { 
         System.out.println("Thread B holding item 2 and waiting for 1");
         synchronized(item1) { 
           System.out.println("Thread B holding item 1");
         }
       }  
       System.out.println("Thread B done.");
     }

     
   }
  
   
 
 //***** 
  public void go() {
    Thread threadA = new Thread(this);
    Thread threadB = new Thread(this);
    
    threadA.setName("A");
    threadB.setName("B");
    
    threadA.start();
    threadB.start();
  }
    public static void main(String[] args) { 
      new ThreadTest7().go();
    }
   
}


