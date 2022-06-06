package Q1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.CyclicBarrier;

public class Zostera extends Immobile
{
    public Zostera(AquaPanel panel,int size,int x,int y)
    {
        super(panel,size,"Zostera",x,y);
    }

    public void drawCreature(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(colorr);
        g.drawLine(x, y, x, y-size);
        g.drawLine(x-2, y, x-10, y-size*9/10);
        g.drawLine(x+2, y, x+10, y-size*9/10);
        g.drawLine(x-4, y, x-20, y-size*4/5);
        g.drawLine(x+4, y, x+20, y-size*4/5);
        g.drawLine(x-6, y, x-30, y-size*7/10);
        g.drawLine(x+6, y, x+30, y-size*7/10);
        g.drawLine(x-8, y, x-40, y-size*4/7);
        g.drawLine(x+8, y, x+40, y-size*4/7);
        g2.setStroke(new BasicStroke(1));
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
