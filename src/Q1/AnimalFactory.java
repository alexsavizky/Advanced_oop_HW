package Q1;

import java.awt.Color;


public class AnimalFactory implements AbstractSeaFactory {

    private int size, horSpeed, verSpeed, foodFreq, x_front, y_front;
    private Color col;
    private AquaPanel panel;

    public AnimalFactory(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col, int foodFreq) {
        this.panel = panel;
        this.size = size;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.x_front = x_front;
        this.y_front = y_front;
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