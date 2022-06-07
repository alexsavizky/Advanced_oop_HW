package Q1;
//token that is written and read by the originator and shepherded by the Caretaker.
public class Memento {
    private MementoState state; // token

    /***
     * @param state state we want to save
     */
    public Memento(MementoState state) {
        this.state = state;
    }

    /***
     * @return the state
     */
    public MementoState getState() {
        return state;
    }
}

