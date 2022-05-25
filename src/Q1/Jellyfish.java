/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */

package Q1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public class Jellyfish extends Swimmable {
	private int E_DISTANCE;						//Amount of food a jellyfish can eat 
	private int size;							//Size of jellyfish
	private Color col;							//Color of jellyfish
	private int eatCount;						//Eat count of jellyfish
	private int x_front, y_front, x_dir, y_dir;	//Position of jellyfish
	private AquaPanel panel;
	private Boolean is_moving = true;
	private CyclicBarrier barrier=null;
	/***
	 * Constructor
	 * @param size - Size of jellyfish 
	 * @param x_front - Position parameter
	 * @param y_front - Position parameter
	 * @param horSpeed - Position parameter
	 * @param verSpeed - Position parameter
	 * @param col - Color of jellyfish
	 */
	public Jellyfish(AquaPanel panel,int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col)
	{
		super(horSpeed, verSpeed);
		this.panel = panel;
		this.size = size;
		this.x_front = x_front;
		this.y_front = y_front;
		this.col = col;
		
		//E_DISTANCE = 4 for checks
		this.E_DISTANCE = 4;
		//Eat count starts with 0
		this.eatCount = 0;
		//Starting position of the jellyfish
		this.x_dir = 1;
		this.y_dir = 1;
	}
	/***
	 * copy constructor
	 * @param other - Jellyfish object
	 * @return copied Jellyfish object
	 */
	public Jellyfish(Jellyfish other) {
		super(other.getHorSpeed(), other.getVerSpeed());
		this.size = other.getSize();
		this.x_front = other.getX_front();
		this.y_front = other.getY_front();
		this.col = other.getCol();
		this.E_DISTANCE = other.getE_DISTANCE();
		this.eatCount = other.getEatCount();
		this.x_dir = other.getX_dir();
		this.y_dir = other.getY_dir();
	}
	
	/***
	 * default constructor
	 * @return default Jellyfish object
	 */
	public Jellyfish() {
		super();
		this.size = 0;
		this.x_front = 0;
		this.y_front = 0;
		this.col = Color.black;
		
		//E_DISTANCE = 4 for checks
		this.E_DISTANCE = 4;
		
		this.eatCount = 0;
		this.x_dir = 1;
		this.y_dir = 1;
		
	}
	
	//get functions
	public String getAnimalName() {return "Jellyfish";}
	public int getEatCount() {return this.eatCount;}
	public int getSize() {return this.size;}
	public int getE_DISTANCE() {return this.E_DISTANCE;}
	public int getX_front() {return this.x_front;}
	public int getY_front() {return this.y_front;}
	public int getX_dir() {return this.x_dir;}
	public int getY_dir() {return this.y_dir;}
	public Color getCol() {return this.col;}
	
	public String getColor()
	{
		if(col == Color.black)
			return "Black";
		else if(col == Color.red)
			return "Red";
		else if(col == Color.blue)
			return "Blue";
		else if(col == Color.green)
			return "Green";
		else if(col == Color.cyan)
			return "Cyan";
		else if(col == Color.orange)
			return "Orange";
		else if(col == Color.yellow)
			return "Yellow";
		else if(col == Color.magenta)
			return "Magneta";
		else
			return "Pink";
	}
	
	/***
	 * Feeding jellyfish func
	 * if the fish is full -> jellyfish's eatCount = 0 & jellyfish's size = size+1 
	 */
	public void eatInc() 
	{
		this.eatCount+=1;
		if (this.eatCount==this.E_DISTANCE)
		{
			this.changeJellyfish(this.size + 1);
			this.eatCount = 0;
		}
	}
	
	//Change the jellyfish's size
	public void changeJellyfish(int a) {this.size = a;}
	
	//Compare a jellyfish for size with: Jellyfish, Fish
	public int comparsize(Object other) {
		if(other instanceof Jellyfish) {
			if(((Jellyfish)other).getSize() > this.getSize())
				return -1;
			if(((Jellyfish)other).getSize() == this.getSize()) 
				return 0;
			return 1;	
			}
		if(other instanceof Fish) {
			if(((Fish)other).getSize() > this.getSize())
				return -1;
			if(((Fish)other).getSize() == this.getSize()) 
				return 0;
			return 1;	
			}
		else {
			return -100;
		}
	}
	//override to string function
	public String toString() {
		return String.format("Jellyfish \n E_DISTANCE :%d \n size :%d \n col :%d \n eatCoun :%d \n x_front :%d \n y_front :%d \n x_dir :%d \n y_dir :%d \n ", E_DISTANCE,size,col,eatCount,x_front,y_front,x_dir,y_dir);
	}
	//override to equals
	public  boolean equals(Object other) {
		if (other != null && other instanceof Jellyfish){
			return (this.size==((Jellyfish)other).getSize() && this.x_front==((Jellyfish)other).getX_front() && this.y_front==((Jellyfish)other).getY_front()
					&& this.col==((Jellyfish)other).getCol()&& this.E_DISTANCE==((Jellyfish)other).getE_DISTANCE()
					&& this.x_dir==((Jellyfish)other).getX_dir()&& this.y_dir==((Jellyfish)other).getY_dir());
		}
		return false;
	}
	@Override
	public void drawCreature(Graphics g)
	{
	   int numLegs;
	   if(size<40)
	    	numLegs = 5;
	   else if(size<80)
	    	numLegs = 9;
	   else
	    	numLegs = 12;

	   g.setColor(col);
	   g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
			
	   for(int i=0; i<numLegs; i++)
		g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);
	}

	
	/***
	 * stop the moving of the jellyfish
	 */
	public void setSuspend() {
		this.is_moving = false;
	}
	
	/***
	 * restart the move of the jellyfish
	 */
	public void setResume() {
		synchronized(this){
			this.is_moving = true;
		
			notify();
		}
	}

	/***
	 * set cyclic barrier
	 */
	public void setBarrier(CyclicBarrier b) {
		this.barrier=b;
		
	}

	/***
	 * run overide function 
	 */
	public void run() {
		while(true) {
			try
			{
				sleep(10);
				if(!panel.is_worm()) {
					if(this.is_moving == true) {	
						moveRandom();
					}
					else {
						synchronized(this){
							wait();
						}
					}
				}
				else {
					if(this.is_moving == true) {	
						movetoFood();
					}
					else {
						synchronized(this){
							wait();
						}
					}
				}
			}catch(InterruptedException e) {}
			panel.repaint();
		}
	}
	/***
	 * move to the center to eat the worm
	 */
	public void movetoFood() {
		if((Math.abs(panel.getWidth()/2-x_front)<=5) && (Math.abs(panel.getHeight()/2-y_front)<=5))
		{
			panel.eatworm();
			this.eatInc();
		}
		else {
			if(this.x_front > panel.getWidth()/2&& x_dir ==1 )
			{
				x_dir =-1;
			}
			if(this.x_front < panel.getWidth()/2&& x_dir ==-1 )
			{
				x_dir =1;
			}

			if(this.y_front > panel.getHeight()/2&& y_dir ==1 )
			{
				y_dir =-1;
			}
			if(this.y_front < panel.getHeight()/2&& y_dir ==-1 )
			{
				y_dir =1;
			}
			if(!(Math.abs(panel.getWidth()/2-x_front)<=5))
			{
				this.x_front += this.horSpeed*this.x_dir;
			}
			if(!(Math.abs(panel.getHeight()/2-y_front)<=5))
				this.y_front += this.verSpeed*this.y_dir;
		}
	}
	/***
	 * moving of the jellyfish without a food 
	 */
	public void moveRandom() {
		if(this.x_front > panel.getWidth()-this.size/2&& x_dir ==1 )
		{
			x_dir =-1;
		}
		else if (this.x_front < this.size/2 && x_dir ==-1 )
		{
			x_dir =1;
		}
		if(this.y_front > panel.getHeight() && y_dir ==1 )
		{
			y_dir =-1;
		}
		else if (this.y_front < 0 && y_dir ==-1 )
		{
			y_dir =1;
		}
		this.x_front += this.horSpeed*this.x_dir;
		this.y_front += this.verSpeed*this.y_dir;
	}
	public Jellyfish clone(){
		return new Jellyfish(panel,size,x_front,y_front,horSpeed,verSpeed,col);
	}
}
