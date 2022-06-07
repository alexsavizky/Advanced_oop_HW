package Q1;

import java.awt.Color;

//Factory class for plants
public class PlantFactory implements AbstractSeaFactory
{
    int size,x,y;       //Plant size and location parameters
    AquaPanel panel;    //AquaPanel object
    Color col;          //Plant's color

    /***
     * Constructor
     * @param panel - AquaPanel object
     * @param size - Plant's size
     * @param x - Plant's X location
     * @param y - Plant's Y location
     */
    public PlantFactory(AquaPanel panel,int size,int x,int y)
    {
        this.panel=panel;
        this.size=size;
        this.x=x;
        this.y=y;
        this.col=Color.green;
    }

    /***
     * Create a plant func
     * @param type - type of desired plant
     * @return - new plant
     */
    public SeaCreature produceSeaCreature(String type)
    {
        if(type.equalsIgnoreCase("Laminaria"))
            return new Laminaria(panel,size,x,y);

        else if(type.equalsIgnoreCase("Zostera")){
            return new Zostera(panel,size,x,y);
        }
        return null;
    }
}
