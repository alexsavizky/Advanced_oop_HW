/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */

package Q1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CyclicBarrier;

public class Fish extends Swimmable implements MarineAnimal{
	private int E_DISTANCE;						//Amount of food a fish can eat 
	private int size;							//Size of fish
	private Color col;							//Color of fish
	private int eatCount;						//Eat count of fish
	private int x_front, y_front, x_dir, y_dir;	//Position of fish
	private AquaPanel panel;					//AquaPanel object
	private Boolean is_moving = true;			//Flag for movement of fish
	private CyclicBarrier barrier=null;			//CyclicBarrier object
	private AquariumActionListener listen;		//Custom ActionListener
	private HungerState myState;				//State of hunger
	private long foodFreq = 20000;				//fish is hungry every 20 seconds
	private Timer timer;						//Timer for hunger
	
	/***
	 * Constructor
	 * @param panel - Main panel passed by reference
	 * @param size - Size of fish 
	 * @param x_front - Position parameter
	 * @param y_front - Position parameter
	 * @param horSpeed - Position parameter
	 * @param verSpeed - Position parameter
	 * @param col - Color of fish
	 */
	public Fish(AquaPanel panel,int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col)
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
		//Starting position of the fish
		this.x_dir = 1;
		this.y_dir = 1;

		//Default state
		myState = new Satiated();
		//Adding a listener
		addActionListener(this.panel);
		//Start hunger timer
		startTimer(foodFreq);
	}


	/***
	 * Starts one round of the timer
	 * @param time - how many milliseconds to execute
	 */
	public void startTimer(long time)
	{
		TimerTask task = new TimerTask() {public void run() {iAmHungry();}};
		timer = new Timer("Timer");
		timer.schedule(task, time);
	}

	/***
	 * default constructor
	 * @return default Fish object
	 */
	public Fish()
	{
		super();
		this.size = 10;
		this.x_front = 1;
		this.y_front = 1;
		this.col = Color.BLACK;
		
		//E_DISTANCE = 4 for checks
		this.E_DISTANCE = 4;
		//Eat count starts with 0
		this.eatCount = 0;
		//Starting position of the fish
		this.x_dir = 1;
		this.y_dir = 1;
	}
	/***
	 * Copy constructor
	 * @param other - Fish object
	 * @return copied Fish object
	 */
	public Fish(Fish other)
	{
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
	
	//get functions
	public String getAnimalName() {return "Fish";}
	public int getEatCount() {return this.eatCount;}
	public int getSize() {return this.size;}
	public int getE_DISTANCE() {return this.E_DISTANCE;}
	public int getX_front() {return this.x_front;}
	final public int getY_front() {return this.y_front;}
	public int getX_dir() {return this.x_dir;}
	public int getY_dir() {return this.y_dir;}
	public Color getCol() {return this.col;}
	public int getAnimalID() {return this.id;}
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
		else if(col == Color.pink)
			return "Pink";
		//If the object changed it's color using a JColorChooser
		else
			return "(" + col.getRed() + ", " + col.getGreen() + ", " + col.getBlue() + ")";
	}
	
	/***
	 * Feeding fish func
	 * if the fish is full -> fish's eatCount = 0 & fish's size = size+1 
	 */
	public void eatInc() 
	{
		this.eatCount+=1;
		if (this.eatCount==this.E_DISTANCE)
		{
			this.changeFish(this.size + 1);
			this.eatCount = 0;
		}
	}
	
	//Change the fish's size
	public void changeFish(int a) {this.size = a;}
	
	//Compare a fish for size with: Fish, Jellyfish
	public int comparsize(Object other) {
		if(other instanceof Fish) {
			if(((Fish)other).getSize() > this.getSize())
				return -1;
			if(((Fish)other).getSize() == this.getSize()) 
				return 0;
			return 1;	
			}
		if(other instanceof Jellyfish) {
			if(((Jellyfish)other).getSize() > this.getSize())
				return -1;
			if(((Jellyfish)other).getSize() == this.getSize()) 
				return 0;
			return 1;	
			}
		else {
			return -100;
		}
	}
	//override to string function
	public String toString() {
		return String.format("Fish \n E_DISTANCE :%d \n size :%d \n col :%d \n eatCoun :%d \n x_front :%d \n y_front :%d \n x_dir :%d \n y_dir :%d \n ", E_DISTANCE,size,col,eatCount,x_front,y_front,x_dir,y_dir);
	}

	public  boolean equals(Object other) {
		if (other != null && other instanceof Jellyfish){
			return (this.size==((Fish)other).getSize() && this.x_front==((Fish)other).getX_front() && this.y_front==((Fish)other).getY_front()
					&& this.col==((Fish)other).getCol()&& this.E_DISTANCE==((Fish)other).getE_DISTANCE()
					&& this.x_dir==((Fish)other).getX_dir()&& this.y_dir==((Fish)other).getY_dir());
		}
		return false;
	}

	/***
	 * Draw the fish
	 */
	public void drawCreature(Graphics g)
	{
	   g.setColor(col);
	   if(x_dir==1) // fish swims to right side
	   {
		// Body of fish
		g.fillOval(x_front - size, y_front - size/4, size, size/2);

		// Tail of fish
		int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size};
		int [] y_t = {y_front - size/4, y_front + size/4, y_front};
		Polygon t = new Polygon(x_t,y_t,3);		
		g.fillPolygon(t);

		// Eye of fish
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255- col.getBlue()));
		g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);
				
		// Mouth of fish
		if(size>70)
			g2.setStroke(new BasicStroke(3));
		else if(size>30)
			g2.setStroke(new BasicStroke(2));
		else
			g2.setStroke(new BasicStroke(1));
	      g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);
	      g2.setStroke(new BasicStroke(1));
	   }
	   else // fish swims to left side
	   {
		// Body of fish
		g.fillOval(x_front, y_front - size/4, size, size/2);

		// Tail of fish
		int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size};
		int [] y_t = {y_front - size/4, y_front + size/4, y_front};
		Polygon t = new Polygon(x_t,y_t,3);		
		g.fillPolygon(t);

		// Eye of fish
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));
		g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);
				
		// Mouth of fish
		if(size>70)
			g2.setStroke(new BasicStroke(3));
		else if(size>30)
			g2.setStroke(new BasicStroke(2));
		else
			g2.setStroke(new BasicStroke(1));
	      g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
	      g2.setStroke(new BasicStroke(1));
	   }
	}
	/***
	 * run override function
	 */
	public void run() {
		while(true) {
			try
			{
				sleep(1000);
				//If worm isn't on screen
				if(!panel.is_worm())
				{
					//If the fish is moving
					if(this.is_moving == true) {
						moveRandom();
					}
					else {
						wait();
					}
				}
				else {
					//If the fish sleeps
					if (this.is_moving == false)
					{
						wait();
					}
					//If the fish is moving and hungry -> swim to food
					else if((this.is_moving == true) && (myState instanceof Hungry))
					{
						movetoFood();
					}
					//The fish isn't hungry -> normal swim
					else
					{
						moveRandom();
					}
				}
			}catch(InterruptedException e) {}
			panel.repaint();
		}
	}

	/***
	 * Stop the moving of the fish
	 */
	public void setSuspend() {
		this.is_moving = false;
		
	}

	/***
	 * Restart the move of the fish
	 */
	public void setResume() {
		synchronized(this){
			this.is_moving = true;
			notify();
		}
	}

	/***
	 * Set cyclic barrier
	 */
	public void setBarrier(CyclicBarrier b) {
		this.barrier=b;
	}

	/***
	 * Move to the center to eat the worm
	 */
	public void movetoFood() {
		//If the fish is close to the worm
		if((Math.abs(panel.getWidth()/2-x_front)<=5) && (Math.abs(panel.getHeight()/2-y_front)<=5))
		{
			panel.eatWorm();
			this.eatInc();
			this.setHungryState(new Satiated());
			this.startTimer(foodFreq);
		}
		//Swim to the worm
		else 
		{
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
	 * Movement of the fish without a food
	 */
	public void moveRandom() {
		synchronized(Fish.class) {
			if (this.x_front > panel.getWidth() - this.size / 4 && x_dir == 1) {
				x_dir = -1;
				this.x_front -= (size / 2 + size / 4);
			} else if (this.x_front < this.size / 4 && x_dir == -1) {
				x_dir = 1;
				this.x_front += (size / 2 + size / 4);
			}
			if (this.y_front > panel.getHeight() - this.size / 4 - 30 && y_dir == 1) {
				y_dir = -1;
			} else if (this.y_front < this.size / 4 && y_dir == -1) {
				y_dir = 1;
			}
			this.x_front += this.horSpeed * this.x_dir * 10;
			this.y_front += this.verSpeed * this.y_dir *10;
		}
	}

	//Clone functions for fish
	public Fish clone(){
		return new Fish(panel,size,x_front,y_front,horSpeed,verSpeed,col);
	}
	public boolean SetClone(int size , int horspeed,int verspeed,Color color){
		this.size = size;
		this.horSpeed = horspeed;
		this.verSpeed = verspeed;
		this.col = color;
		return true;
	}

	@Override
	/***
	 * Re-create the fish using previous parameters
	 */
	public boolean SetMementoState(MementoState state) {
		this.col = state.color;
		this.x_front = state.x;
		this.y_front = state.y;
		this.horSpeed =state.hor_speed;
		this.verSpeed = state.ver_speed;
		this.size = state.size;
		return true;
	}

	/***
	 * The fish informs he's hungry
	 */
	public void iAmHungry()
	{
		listen.actionHungryFish(this);
	}

	//Add a listener
	public void addActionListener(AquariumActionListener aal)
	{
		this.listen = aal;
	}
	//Remove a listener
	public void RemoveListen(){
		this.timer.cancel();
		this.listen = null;
	}

	/***
	 * Change the color of the fish
	 * @param col - Desired color
	 */
	public void PaintFish(Color col){this.col = col;}

	/***
	 * Change the hunger state of the fish
	 * @param state - Desired state
	 */
	public void setHungryState(HungerState state) {this.myState = state;}
}