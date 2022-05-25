package Q1;

public class Singleton {
    private static Singleton instance = null;

    public static Singleton getInstance(){

        if (instance==null){
            instance=new Singleton();
        }

        return instance;
    }

    //intialize the instance to null after the thread ate the instance - worm
    public static void set(){
        if(instance!=null)
        {
            instance=null;
        }
    }
    public static Singleton get()
    {
        return Singleton.instance;
    }
}
