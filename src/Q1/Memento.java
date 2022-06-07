package Q1;

public class Memento {
    private MementoState state;

    public Memento(MementoState state) {
        this.state = state;
    }

    public MementoState getState() {
        return state;
    }
}

