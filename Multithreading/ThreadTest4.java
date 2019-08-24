/* ThreadTest4 - Demonstrating the concurrency of multiple threads
 * 
 * There is no way to know the order the threads as they execute
 * The system decides and schedules the threads
 * 
 * Mangat
 */
class ThreadTest4 implements Runnable {  
  
  //***
  
  protected double count = 0;

   
  //***
   public void run() { 
  
    for(int i=0;i<1000;i++) {
      
      add(1);
      subtract(1);
      System.out.println("Thread B is Running!" + count);

       
      try{
        Thread.sleep(1);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
   }
   
   //***
     public void add(long value){
         this.count = this.count + value;
     }
    public void subtract(long value){
         this.count = this.count - value;
     }
  
  
   ///**
  
   
   
   

 //***** 
  public void go() {
    Thread threadB = new Thread(this);
    threadB.start();
    
   for(int i=0;i<1000;i++) {
     
      add(1);
      subtract(1);      
      System.out.println("Thread A is Running!" + count);

      
      try{
        Thread.sleep(1);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
  }
  //***
    public static void main(String[] args) { 
      new ThreadTest4().go();
    }
  
  
}
