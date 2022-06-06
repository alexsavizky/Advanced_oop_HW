package Q1;

public class Satiated implements HungerState
{
    public void ChangeState(Swimmable fishy) {fishy.setHungryState(this);}
    public String toString() {return "Satiated";}
}
