package Q1;

public class Singleton {
    private static Singleton instance = null;

    /***
     * @return a new Singleton if doesn't exist \ return the Singleton if exist
     */
    public static Singleton getInstance(){
        if (instance==null){
            instance=new Singleton();
        }
        return instance;
    }

    /***
     * Intialize the instance to null after the thread ate the instance - worm
     */
    public static void set(){
        if(instance!=null)
        {
            instance=null;
        }
    }

    /***
     * @return the Singleton instance
     */
    public static Singleton get()
    {
        return Singleton.instance;
    }
}
