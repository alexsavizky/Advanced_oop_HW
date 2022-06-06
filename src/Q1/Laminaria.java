package Q1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.CyclicBarrier;

public class Laminaria extends Immobile
{
    public Laminaria(AquaPanel panel,int size,int x,int y)
    {
        super(panel,size,"Laminaria",x,y);
    }

    public void drawCreature(Graphics g)
    {
        g.setColor(colorr);
        g.fillArc(x-size/20, y-size, size/10, size*4/5,0, 360);
        g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3,0, 360);
        g.fillArc(x+size/20,y-size*13/15,size/10, size*2/3, 0, 360);
        g.drawLine(x, y, x, y-size/5);
        g.drawLine(x,y,x-size/10,y-size/5);
        g.drawLine(x, y, x+size/10, y-size/5);
    }

    @Override
    public int getPlantId() {
        return this.id;
    }

    @Override
    public boolean SetMementoState(MementoState state) {
        this.x = state.x;
        this.y = state.y;
        this.colorr = state.color;
        return true;
    }
}
