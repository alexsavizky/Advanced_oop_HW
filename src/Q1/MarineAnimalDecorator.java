package Q1;

import java.awt.Color;

//Class to change color of a marine animal
public class MarineAnimalDecorator implements MarineAnimal
{
    public MarineAnimal myAnimal;

    /***
     * Constructor
     * @param myAnimal - Animal to color
     */
    public MarineAnimalDecorator(MarineAnimal myAnimal)
    {
        this.myAnimal = myAnimal;
    }

    /***
     * Paint the animal
     * @param col - Desired new color
     */
    public void PaintFish(Color col)
    {
        myAnimal.PaintFish(col);
    }
}