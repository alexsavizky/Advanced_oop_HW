/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */
package Q1;

import java.awt.Color;
//Animal Factory creates all the animal in the project
public class AnimalFactory implements AbstractSeaFactory {
    private int size, horSpeed, verSpeed, x_front, y_front;
    private Color col;
    private AquaPanel panel;

    /***
     *Constructor
     * @param panel - main panel passed by reference
     * @param size - size of the animal
     * @param x_front - x coordinate of the animal
     * @param y_front - y coordinate of the animal
     * @param horSpeed - horizontal speed of the animal
     * @param verSpeed - vertical speed of the animal
     * @param col - color of the animal
     */
    public AnimalFactory(AquaPanel panel, int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col) {
        this.panel = panel;
        this.size = size;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = col;
    }

    /***
     *
     * @param type - fish or jellyfish in order to know which seaCreature initialize
     * @return new Sea Creature fish or jellyfish according to type
     */
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