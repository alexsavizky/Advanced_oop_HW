/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */

package Q1;

import java.awt.*;
//import java.util.Observable;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Thread{
	protected static int counter = 0; 
	private int id;
	protected int horSpeed; //Horizontal speed
	protected int verSpeed; //Vertical speed
	
	/***
	 * Default constructor
	 */
	public Swimmable()
	{
		Swimmable.counter+=1;
		this.id = counter;
		this.horSpeed = 0;
		this.verSpeed = 0;
	}
	
	/***
	 * Constructor
	 * @param a - Horizontal speed
	 * @param b - Vertical speed
	 */
	public Swimmable(int a, int b)
	{
		Swimmable.counter+=1;
		this.id = counter;
		this.horSpeed = a;
		this.verSpeed = b;
	}
	
	//get functions
	public int getHorSpeed() {return this.horSpeed;}
	public int getVerSpeed() {return this.verSpeed;}
	
	
	//set functions for horSpeed & verSpeed
	public boolean setHorSpeed(int a) 
	{
		if (a >=0 && a<=1000)
		{
			this.horSpeed = a;
			return true;}
		else
			return false;
	}
	public boolean setVerSpeed(int b) 	
	{
		if (b >=0 && b<=1000)
		{
			this.verSpeed = b;
			return true;}
		else
			return false;
	}
	
	//Functions for Fish & Jellyfish classes
	abstract public String getAnimalName();
	abstract public void drawAnimal(Graphics g);
	abstract public void setSuspend();
	abstract public void setResume();
	abstract public void setBarrier(CyclicBarrier b);
	abstract public int getSize();
	abstract public void eatInc();
	abstract public int getEatCount();
	abstract public String getColor();
	abstract public void run();
}
