package Q1;

public class Hungry implements HungerState
{
    /***
     * change the state of hunger
     * @param fishy - swimmable
     */
    public void ChangeState(Swimmable fishy) {fishy.setHungryState(this);}

    /***
     * @return string of the hunger state
     */
    public String toString() {return "Hungry";}
}
