package Q1;

import java.util.ArrayList;
import java.util.Objects;
//hold the state of the originator and ask to restore it when needed
public class Caretaker {
    private ArrayList<Memento> mementos = new ArrayList<Memento>(); //memento array list

    /***
     * add memento token to caretaker
     * @param m - memento token
     */
    public void addMemento(Memento m) {
        mementos.add(m);
    }

    /***
     *return memento token by date
     * @param date - string in format "HH:mm:ss"
     * @return memento token
     */
    public Memento getMemento(String date) {
        for(int i=0 ; i<mementos.size() ; i++){
            if (Objects.equals(mementos.get(i).getState().getTimeSaved(), date)){
                return mementos.get(i);
            }
        }
        return null;
    }

    /***
     *return memento token by index
     * @param index - int
     * @return memento token
     */
    public Memento getMemento(int index){
        return mementos.get(index);
    }

    /***
     * @return how many memento token there is in the care taker
     */
    public int getSize(){return mementos.size();}
}
