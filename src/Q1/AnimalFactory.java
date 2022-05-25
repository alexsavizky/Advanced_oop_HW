package Q1;

import java.awt.Color;


public class AnimalFactory implements AbstractSeaFactory {

    private int size, horSpeed, verSpeed, foodFreq;
    private Color col;
    private AquaPanel panel;

    public AnimalFactory(AquaPanel panel, int size, int horSpeed, int verSpeed, Color col, int foodFreq) {
        this.panel = panel;
        this.size = size;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.col = col;
        this.foodFreq = foodFreq;
    }

    public SeaCreature produceSeaCreature(String type) {
        if (type.equalsIgnoreCase("Fish")) {
            return new Fish(panel,size,x_front,y_front,horSpeed,verSpeed,col);
        } else if (type.equalsIgnoreCase("Jellyfish"))
            return new Jellyfish(panel,size,x_front,y_front,horSpeed,verSpeed,col);

        return null;
    }
}