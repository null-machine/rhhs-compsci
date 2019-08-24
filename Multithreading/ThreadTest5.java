/* ThreadTest3 - Demonstrating 
 * static methods
 * Thread.sleep() -  put the current thread of execution to sleep for an approximate amount of time
 * Thread.yield() - (static) the current thread becomes available for rescheduling
 * Thread.currentThread() - get a reference to the current thread of execution
 * Thread.activeCount() - number of active threads (approximate)
 * 
 * Non-static
 * interupt() - wake up a thread
 * setPriority()/getPriority - user priorty (System dependant, usually 1 - 10)
 * join() - the current thread will wait for another thread to complete
 * getName()/setName() - helps to identify threads
 * 
 * Mangat
 */
class ThreadTest5 implements Runnable {  
  
  //***
   public void run() { 
      System.out.println(Thread.currentThread().getName() + " started.(run)");
    for(int i=0;i<3;i++) {
      
      System.out.println("Thread " + Thread.currentThread().getName() + " is running");
      try{
        //Thread.sleep(10);
        Thread.yield();
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
   }
   

 //***** 
  public void go() {
    Thread threadA = new Thread(this);
    threadA.setName("AAA");
    threadA.setPriority(Thread.MIN_PRIORITY);
       
    Thread threadB = new Thread(this);
    threadB.setName("BBB");
    threadB.setPriority(Thread.MIN_PRIORITY);
    
    threadA.start();

  /* Uncomment to see thread join method */ 
    try{
        threadA.join(); //wait for threadA to finish
      }catch(Exception e) { 
        e.printStackTrace();
      }
    
    
    threadB.start();
    

    System.out.println(Thread.currentThread().getName() +" started. (go)");
    System.out.println("Currently: " + Thread.activeCount() + " threads running.");
  }
  
  //***
    public static void main(String[] args) { 
     System.out.println(Thread.currentThread().getName()+"  started. (main)");
      new ThreadTest5().go(); 
      
        
    }
  
  
}
