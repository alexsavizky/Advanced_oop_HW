package Q1;

public class Originator {
    private MementoState state;

    public void setState(MementoState state) {
        System.out.println("Originator: Setting state to " + state);
        this.state = state;
    }
    public MementoState getState(){
        return state;
    }

    public Memento save() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state);
    }
    public void restore(Memento m) {
        state = m.getState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }
}
