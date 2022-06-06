package Q1;

public class Originator {
    private MementoState state;

    public void setState(MementoState state) {
        this.state = state;
    }
    public MementoState getState(){
        return state;
    }

    public Memento save() {
        return new Memento(state);
    }
    public void restore(Memento m) {
        state = m.getState();
    }
}
