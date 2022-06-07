package Q1;
//The object that knows how to save itself
public class Originator {
    private MementoState state;// State

    /***
     * Set the state
     * @param state
     */
    public void setState(MementoState state) {
        this.state = state;
    }

    /***
     * @return Memento state
     */
    public MementoState getState(){
        return state;
    }

    /***
     * @return new memento token
     */
    public Memento save() {
        return new Memento(state);
    }

    /***
     * restore to the last state
     * @param m - memento token
     */
    public void restore(Memento m) {
        state = m.getState();
    }
}
