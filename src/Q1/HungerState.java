package Q1;
//interface that manage the hunger state of animal
public interface HungerState
{
    /***
     * change the state of hunger
     * @param obj_swim - swimmable
     */
    void ChangeState(Swimmable obj_swim);
    /***
     * @return string of the hunger state
     */
    String toString();
}
