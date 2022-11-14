package Q1;

import java.awt.Graphics;

public class Laminaria extends Immobile
{
    /***
     * @param panel - AquaPanel object
     * @param size - The Laminaria size
     * @param x - X spawn location
     * @param y - Y spawn location
     */
    public Laminaria(AquaPanel panel,int size,int x,int y)
    {
        super(panel,size,"Laminaria",x,y);
    }

    /***
     * Drawing a Laminaria func
     */
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
    /***
     * Get Laminaria ID number
     */
    public int getPlantId() {
        return this.id;
    }

    @Override
    /***
     * @param state
     * @return True if the state has changed
     */
    public boolean SetMementoState(MementoState state) {
        this.x = state.x;
        this.y = state.y;
        this.colorr = state.color;
        return true;
    }
}
