/* ThreadTest6 - Demonstrating the Thread syncronization
 * The add and delete methods will never be available for other
 * threads when they are being used
 * Mangat
 */
class ThreadTest6 implements Runnable {  
  
  //***
  
  protected long count = 0;

   
  //***
   public void run() { 
  
    for(int i=0;i<1000;i++) {
      
      add(1);
      subtract(1);
      System.out.println("Thread B is Running!" + count);

       
      try{
        Thread.sleep(10);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
   }
   
   //***
     public synchronized void add(long value){
         this.count = this.count + value;
     }
    public synchronized void subtract(long value){
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
        Thread.sleep(10);
      }catch(Exception e) { 
        e.printStackTrace();
      }
      
    }
  }
  //***
    public static void main(String[] args) { 
      new ThreadTest6().go();
    }
  
  
}
