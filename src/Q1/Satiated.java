package Q1;

//Satiated class for Fish & Jellyfish
public class Satiated implements HungerState
{
    public void ChangeState(Swimmable fishy) {fishy.setHungryState(this);}
    public String toString() {return "Satiated";}
}
