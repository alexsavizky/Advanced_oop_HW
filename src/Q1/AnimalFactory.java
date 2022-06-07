package Q1;

import java.awt.Color;


public class AnimalFactory implements AbstractSeaFactory {

    private int size, horSpeed, verSpeed, x_front, y_front;
    private Color col;
    private AquaPanel panel;

    public AnimalFactory(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col) {
        this.panel = panel;
        this.size = size;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = col;
    }


    public SeaCreature produceSeaCreature(String type) {
        if (type.equalsIgnoreCase("Fish"))
        {
            Fish f = new Fish(panel,size,x_front,y_front,horSpeed,verSpeed,col);
            return f;
        } else if (type.equalsIgnoreCase("Jellyfish"))
        {
            Jellyfish j = new Jellyfish(panel, size, x_front, y_front, horSpeed, verSpeed, col);
            return j;
        }
        return null;
    }
}