package Q1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//costume action listener
public interface AquariumActionListener extends ActionListener
{

    void actionPerformed(ActionEvent e);

    /***
     * costume listener functionality
     * @param s - swimmable obj
     */
    void actionHungryFish(Swimmable s);
}