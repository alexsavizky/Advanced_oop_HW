package Q1;

import java.awt.Color;

public class MarineAnimalDecorator implements MarineAnimal
{
    public MarineAnimal myAnimal;

    public MarineAnimalDecorator(MarineAnimal myAnimal)
    {
        this.myAnimal = myAnimal;
    }

    public void PaintFish(Color col)
    {
        myAnimal.PaintFish(col);
    }

}