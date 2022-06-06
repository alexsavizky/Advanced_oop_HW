/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */

package Q1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;


public class AquaPanel extends JPanel implements AquariumActionListener
{
	private static final long serialVersionUID = 1L;
	
	//AddAnimalDialog for use
	private AddAnimalDialog aad;
	private AddPlantDialog apd;
	
	//Background image for panel
	protected Image background = null;
	
	//Buttons, labels, tables for panel
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
	private JPanel buttons;
	private JLabel picLabel;
	private JTable table;
	private JScrollPane jsc;
	private JDialog decoratorDialog;
	private JPanelDecorator decorator;
	
	//Swimmable hashset
	private HashSet<Swimmable> swimSet = new HashSet<Swimmable>();
	private Iterator <Swimmable>itrAnimals;

	private HashSet<Immobile> immobileSet = new HashSet<Immobile>();
	private Iterator <Immobile>itrPlants;

	//Flags for worm and info
	private Singleton wormsingle = null;
	private Boolean infoFlag = false;
	

	//default constructor
	public AquaPanel() 
	{
		super();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		//Make buttons for panel function
		MakeButtons();
	}
	
	//Get function for swimset size
	public int getSwimSetSize() {return swimSet.size();}
	public  int getImmobileSetSize() {return  immobileSet.size();}
	public HashSet<Swimmable> getSwimSet(){return swimSet;}


	//Create the buttons for the panel
	public void MakeButtons() 
	{
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(0,10,0,0));
		b1 = new JButton("Add Animal");
		b2 = new JButton("Duplicate Animal");
		b3 = new JButton("Add Plant");
        b4 = new JButton("Sleep");
        b5 = new JButton("Wake up");
        b6 = new JButton("Reset");
        b7 = new JButton("Food");
		b8 = new JButton("Decorator");
		b9 = new JButton("Info");
        b10 = new JButton("Exit");

        
        //Adding to buttons
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		buttons.add(b5);
		buttons.add(b6);
		buttons.add(b7);
		buttons.add(b8);
		buttons.add(b9);
		buttons.add(b10);
		
		//Placing in south of the screen
		add(buttons,BorderLayout.SOUTH);
		
		//Adding action listeners
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
	}
	
	//Functionality for buttons
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == b1) 						//CLICK ON "Add Animal" - B1
		{
			if (infoFlag == true)
			{
				remove(jsc);
				validate();
				repaint();
				infoFlag = false;
			}
			aad = new AddAnimalDialog(this);
			aad.setVisible(true);
		}

		if (e.getSource() == b3) 						//CLICK ON "Add Plant" - B3
		{
			apd = new AddPlantDialog(this);
			apd.setVisible(true);
		}

		else if(e.getSource()== b4) {					//CLICK ON "Sleep" - B4
			itrAnimals= swimSet.iterator(); 
		 	while(itrAnimals.hasNext()){
		 		itrAnimals.next().setSuspend(); 
		 	}
		}
		else if(e.getSource()== b5) {					//CLICK ON "Wake up" - B5
			itrAnimals= swimSet.iterator(); 
		 	while(itrAnimals.hasNext()){
		 		itrAnimals.next().setResume(); 
		 	}
		}



		else if(e.getSource() == b6) {					//CLICK ON "Reset" - B6
			Iterator<Swimmable> iterator = swimSet.iterator();

			if (iterator.hasNext())
			{
				iterator.next().setSuspend();
				iterator.remove();
			}

			immobileSet = new HashSet<Immobile>();
			swimSet = new HashSet<Swimmable>();
			repaint();

			if(wormsingle != null)
				eatworm();

			infoFlag = false;

		}

		else if(e.getSource() == b7) {					//CLICK ON "Food" - B7
			if (wormsingle == null)
			{
				try {
					//Adding a picture of a worm
					picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("src/Caterpie-icon.png"))));
					add(picLabel,BorderLayout.CENTER);
					this.validate();
					//repaint();
					}
				catch (IOException e1) {
					e1.printStackTrace();
				}
				Singleton.set();
				wormsingle = Singleton.getInstance();
			}

		}

		else if(e.getSource() == b8) 					//CLICK ON "Decorator" - B8
		{
			decorator = new JPanelDecorator(this);
			repaint();
		}

		else if(e.getSource() == b9) 					//CLICK ON "Info" - B9
		{
			
			if (infoFlag == false) 
			{
				String name, color;
				int size, h, v, food, id, freq, all = 0;
				itrAnimals = swimSet.iterator();
				Swimmable s;
				
				//Info table columns
				table = new JTable(new DefaultTableModel(new Object[]{ "ID", "Animal", "Color", "Size", "Hor. Speed",
						"Ver. Speed", "Hunger Freq","Eat counter"}, 0));
				table.setAutoCreateRowSorter(true);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				while(itrAnimals.hasNext()) 
				{
					s = itrAnimals.next();
					id = s.getAnimalID();
					name = s.getAnimalName();
					color = s.getColor();
					size = s.getSize();
					h = s.getHorSpeed();
					v = s.getVerSpeed();
					if (name == "Fish")
						freq = 20;
					else
						freq = 25;

					food = s.getEatCount();
					
					all += food;
					model.addRow(new Object[]{id, name, color, String.valueOf(size), String.valueOf(h),
							String.valueOf(v), "Every " + String.valueOf(freq) + " seconds", String.valueOf(food)});
				}
				
				//Adding a 'Total' row to table
				model.addRow(new Object[]{"", "", "", "", "", "", "", "Total eat count: " + String.valueOf(all)});
				
				jsc = new JScrollPane(table);
				add(jsc);
				validate();
				infoFlag = true;
			}
			//Hiding info
			else 
			{
				remove(jsc);
				validate();
				repaint();
				infoFlag = false;
			}
		}
		else if (e.getSource() == b10)				//CLICK ON "Exit" - B10
			System.exit(0);
	}

	//Paint component function
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
        Graphics2D G = (Graphics2D) g;
        G.drawImage(this.background,0,0,getWidth(),getHeight(),this);
        itrAnimals= swimSet.iterator(); //intialzie iterator
		itrPlants = immobileSet.iterator();
		while(itrPlants.hasNext())
			itrPlants.next().drawCreature(g);
        while(itrAnimals.hasNext())
	 		itrAnimals.next().drawCreature(g);
	}
	
	//Add an animal to the swimset
	public void addAnimal(Swimmable swim) 
	{
		//swim.addObserver(this);
//		swim.addActionListener(this);
		swimSet.add(swim);
		repaint();
		swim.start();
	}

	public void addPlant(Immobile imm){
		immobileSet.add(imm);
		repaint();
	}
	
	//Eat a worm function
	public void eatworm() 
	{
		wormsingle = null;
		this.remove(picLabel);
		this.revalidate();
		this.repaint();
	}
	
	//Get function for flag of worm
	public Boolean is_worm() 
	{
		return wormsingle!=null;
	}
	public void setWormInstance()
	{
		Singleton.set();
		wormsingle=null;
	}
	public Singleton getWormInstance(){return wormsingle;}

/*	public void update(Observable a, Object obj)
	{
		JOptionPane.showMessageDialog(null, obj + " is hungry!",
				"Time to eat", JOptionPane.PLAIN_MESSAGE);
	}*/

	/////////////////////OBSERVER SHIT//////////////////////
	public void actionHungryFish(Swimmable s)
	{
		//s.setHungryState(new Hungry());
		HungerState hs = new Hungry();
		hs.ChangeState(s);

		if (!is_worm())
			JOptionPane.showMessageDialog(null, "Animal: " + s.getAnimalName() + "\nID:" +
					s.getAnimalID() + "\nFeed it using the 'Food' button", "Your animal is hungry!",
					JOptionPane.INFORMATION_MESSAGE);
	}
}