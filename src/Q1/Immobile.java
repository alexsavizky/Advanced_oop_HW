package Q1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.CyclicBarrier;

public abstract class Immobile implements SeaCreature
{
    protected static int counter = 0;
    protected int id;

    protected String name;
    protected int size,x,y;
    protected Color colorr;
    protected AquaPanel panel;

    public Immobile(AquaPanel panel,int size,String name,int x,int y)
    {
        Immobile.counter+=1;
        this.id = counter;
        this.name=name;
        this.panel=panel;
        this.size=size;
        this.x=x;
        this.y=y;
        this.colorr=Color.green;
    }
    abstract public int getPlantId();
    abstract public boolean SetMementoState(MementoState state);
}