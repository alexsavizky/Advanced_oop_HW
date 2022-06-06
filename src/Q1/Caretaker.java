package Q1;

import java.util.ArrayList;
import java.util.Objects;

public class Caretaker {
    private ArrayList<Memento> mementos = new ArrayList<Memento>();

    public void addMemento(Memento m) {
        mementos.add(m);
    }

    public Memento getMemento(String date) {
        for(int i=0 ; i<mementos.size() ; i++){
            if (Objects.equals(mementos.get(i).getState().gettimesaved(), date)){
                return mementos.get(i);
            }
        }
        return null;
    }
    public Memento getMemento(int index){
        return mementos.get(index);
    }
    public int getsize(){return mementos.size();}
}
