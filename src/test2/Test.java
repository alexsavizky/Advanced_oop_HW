package test2;

public class Test extends Thread {
    private String shpih ;
    private int id;
    Object o = new Object();
    public Test(String name , int id){
        shpih = name;
        this.id = id;
    }
    public void run(){
        for(int i=0;i<3;i++){
            try {
                sleep(1000);
                print();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            print();
        }
    }
    synchronized public void print(){

         System.out.println(shpih);
         System.out.println(id);

    }
    public static void main(String[] args){
        Thread a = new Test("shpih a",1);
        Thread b = new Test("shpih b",2);
        Thread c = new Test("shpih c",3);
        a.start();
        b.start();
        c.start();
    }
}
