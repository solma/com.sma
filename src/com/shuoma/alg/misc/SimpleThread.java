

public class SimpleThread implements Runnable{
   public static void main (String[] args){
      new SimpleThread().main();
   }

   void main(){
     long start_time=System.currentTimeMillis();
     Thread t=new Thread(new SimpleThread());
     t.start();
     try{
        while(System.currentTimeMillis()-start_time<=2000&&t.isAlive()){
           t.join(1000);
           System.out.println("...One Second Passed...");
        }
        if(t.isAlive()) t.interrupt();
     }catch(Exception e){
       e.printStackTrace();
     }
   }

   public void run(){
     String[] ss={"One car", "two planes", "three ships"};
        try{
          for(int i=0;i<ss.length;i++){
            Thread.sleep(1000);
            System.out.println(ss[i]);
          }
        }catch(Exception e){
          System.out.println("I am not finished yet.");
          e.printStackTrace();
        }      
   }
}