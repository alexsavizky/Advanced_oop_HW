package Q1;

import java.awt.Color;

public class PlantFactory implements AbstractSeaFactory
{
    int size,x,y;
    AquaPanel panel;
    Color col;
    public PlantFactory(AquaPanel panel,int size,int x,int y)
    {
        this.panel=panel;
        this.size=size;
        this.x=x;
        this.y=y;
        this.col=Color.green;
    }
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
