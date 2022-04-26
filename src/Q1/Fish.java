
package Q1;

import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public class Fish extends Swimmable {
	private int E_DISTANCE;						//Amount of food a fish can eat 
	private int size;							//Size of fish
	private int col;							//Color of fish
	private int eatCount;						//Eat count of fish
	private int x_front, y_front, x_dir, y_dir;	//Position of fish
	
	/***
	 * Constructor
	 * @param size - Size of fish 
	 * @param x_front - Position parameter
	 * @param y_front - Position parameter
	 * @param horSpeed - Position parameter
	 * @param verSpeed - Position parameter
	 * @param col - Color of fish
	 */
	public Fish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col)
	{
		super(horSpeed, verSpeed);
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
	}
	
	/***
	 * Default constructor
	 * @param size - Size of fish 
	 * @param x_front - Position parameter
	 * @param y_front - Position parameter
	 * @param horSpeed - Position parameter
	 * @param verSpeed - Position parameter
	 * @param col - Color of fish
	 */
	public Fish()
	{
		super();
		this.size = 10;
		this.x_front = 1;
		this.y_front = 1;
		this.col = 1;
		
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
	public String getAnimalName() {return getClass().getName();}
	public int getEatCount() {return this.eatCount;}
	public int getSize() {return this.size;}
	public int getE_DISTANCE() {return this.E_DISTANCE;}
	public int getX_front() {return this.x_front;}
	public int getY_front() {return this.y_front;}
	public int getX_dir() {return this.x_dir;}
	public int getY_dir() {return this.y_dir;}
	public int getCol() {return this.col;}
	public String getColor()
	{
		switch(this.col) {
		case 1:
			return "Black";
		case 2:
			return "Red";
		case 3:
			return "Blue";
		case 4:
			return "Green";
		case 5:
			return "Cyan";
		case 6:
			return "Orange";
		case 7:
			return "Yellow";
		case 8:
			return "Magneta";
		case 9:
			return "Pink";
		default:
			return "Black";
		}
	}
	
	/***
	 * Feeding fish func
	 * if the fish is full -> fish's eatCount = 0 & fish's size = size+1 
	 */
	public void eatInc() 
	{
		this.eatCount+=1;
		if (this.eatCount==4)
		{
			this.changeFish(this.size + 1);
			this.eatCount = 0;
		}
	}
	
	//Change the fish's size
	public void changeFish(int a) {this.size = a;}
	
	//Change the fish's color
	public void changeColor(){
		if (this.col < 9)
			this.col += 1;
		else
			this.col = 1;
	}
	
	
	//Compare a fish for size with: Fish, Jellyfish, UnusualFish
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
	//override to equals
	public  boolean equals(Object other) {
		if (other != null && other instanceof Jellyfish){
			return (this.size==((Fish)other).getSize() && this.x_front==((Fish)other).getX_front() && this.y_front==((Fish)other).getY_front()
					&& this.col==((Fish)other).getCol()&& this.E_DISTANCE==((Fish)other).getE_DISTANCE()
					&& this.x_dir==((Fish)other).getX_dir()&& this.y_dir==((Fish)other).getY_dir());
		}
		return false;
	}

	@Override
	public void drawAnimal(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSuspend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBarrier(CyclicBarrier b) {
		// TODO Auto-generated method stub
		
	}
}

