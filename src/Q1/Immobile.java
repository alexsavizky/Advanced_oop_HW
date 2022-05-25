package Q1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.CyclicBarrier;

public abstract class Immobile implements SeaCreature
{
    private String name;
    private static int counter=0;
    public final int objectID;
    protected int size,x,y;
    protected Color colorr;
    protected AquaPanel panel;
    public Immobile(AquaPanel panel,int size,String name,int x,int y){
        this.objectID=++counter;
        this.name=name;
        this.panel=panel;
        this.size=size;
        this.x=x;
        this.y=y;
        this.colorr=Color.green;
    }
}