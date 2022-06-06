package Q1;

public class Hungry implements HungerState
{
    public void ChangeState(Swimmable fishy) {fishy.setHungryState(this);}
    public String toString() {return "Hungry";}
}
