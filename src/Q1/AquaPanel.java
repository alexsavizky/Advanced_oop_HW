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
import java.io.File;
import java.io.IOException;
import java.util.HashSet;


public class AquaPanel extends JPanel implements AquariumActionListener
{
	private static final long serialVersionUID = 1L;
	
	//AddAnimalDialog for use
	private AddAnimalDialog aad;
	//AddPlantDialog for use
	private AddPlantDialog apd;
	//DuplicateAnimalDialog for use
	private DuplicateAnimalDialog dad;
	
	//Background image for panel
	protected Image background = null;
	
	//Buttons, labels, tables for panel
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
	private JPanel buttons;
	private JLabel picLabel;
	private JTable table;
	private JScrollPane jsc;

	private JPanelDecorator decorator;

	
	//Swimmable hashset
	private HashSet<Swimmable> swimSet = new HashSet<Swimmable>();
	//Immobile hashset
	private HashSet<Immobile> immobileSet = new HashSet<Immobile>();

	//Singleton for the worm
	private Singleton worm_single = null;
	private Boolean infoFlag = false;
	//memento management
	private Caretaker caretaker;


	/***
	 * Constructor
	 */
	public AquaPanel()
	{
		super();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		this.caretaker = caretaker;
		//Make buttons for panel function
		MakeButtons();
	}

	/***
	 * make buttons
	 */
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

	/***
	 * preform the buttons functionality
	 * @param e the event to be processed
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//CLICK ON "Add Animal" - B1
		if (e.getSource() == b1)
		{
			addAnimalBtn();
		}

		//CLICK ON "Duplicate Animal" - B2
		else if(e.getSource() ==b2)
		{
			dad = new DuplicateAnimalDialog(this);
			dad.setVisible(true);
		}

		//CLICK ON "Add Plant" - B3
		if (e.getSource() == b3)
		{
			apd = new AddPlantDialog(this);
			apd.setVisible(true);
		}

		//CLICK ON "Sleep" - B4
		else if(e.getSource()== b4) {
		 	for(Swimmable i : swimSet)
		 		i.setSuspend();
		}

		//CLICK ON "Wake up" - B5
		else if(e.getSource()== b5) {
			for(Swimmable i : swimSet)
				i.setResume();
		}

		//CLICK ON "Reset" - B6
		else if(e.getSource() == b6) {
			resetBtn();
		}

		//CLICK ON "Food" - B7
		else if(e.getSource() == b7) {
			foodBtn();
		}

		//CLICK ON "Decorator" - B8
		else if(e.getSource() == b8)
		{
			decorator = new JPanelDecorator(this);
			repaint();
		}

		//CLICK ON "Info" - B9
		else if(e.getSource() == b9)
			infoBtn();

		//CLICK ON "Exit" - B10
		else if (e.getSource() == b10)
			System.exit(0);


	}

	/***
	 * paint the objects of the panel
	 * @param g the <code>Graphics</code> object to protect
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
        Graphics2D G = (Graphics2D) g;
        G.drawImage(this.background,0,0,getWidth(),getHeight(),this);
		for(Swimmable i : swimSet)
			i.drawCreature(g);
		for(Immobile i: immobileSet)
			i.drawCreature(g);
	}

	/***
	 * Add swim a Swimmable object to the swim set
	 * @param swim - the swimmable you want to add to the hash set
	 */
	public void addAnimal(Swimmable swim) 
	{
		swimSet.add(swim);
		repaint();
		swim.start();
	}

	/***
	 * Add imm an Immobile object to the immobile set
	 * @param imm - the Immobile you want to add to the hash set
	 */
	public void addPlant(Immobile imm){
		immobileSet.add(imm);
		repaint();
	}

	/***
	 * eat the worm on the screen
	 */
	public void eatWorm()
	{
		worm_single = null;
		this.remove(picLabel);
		this.revalidate();
		this.repaint();
	}

	/***
	 * check in there is a singleton instance
	 * @return true if there is a worm else false
	 */
	public Boolean is_worm() {return worm_single !=null;}

	/***
	 * help function for info button
	 */
	private void infoBtn(){
		if (infoFlag == false)
		{
			if (worm_single !=null)
				this.remove(picLabel);

			String name, color;
			int size, h, v, food, id, freq, all = 0;

			//Info table columns
			table = new JTable(new DefaultTableModel(new Object[]{ "ID", "Animal", "Color", "Size", "Hor. Speed",
					"Ver. Speed", "Hunger Freq","Eat counter"}, 0));
			table.setAutoCreateRowSorter(true);
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			//insert values into the table
			for(Swimmable i : swimSet)
			{
				id = i.getAnimalID();
				name = i.getAnimalName();
				color = i.getColor();
				size = i.getSize();
				h = i.getHorSpeed();
				v = i.getVerSpeed();
				if (name == "Fish")
					freq = 20;
				else
					freq = 25;

				food = i.getEatCount();

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
			if (worm_single !=null)
				this.add(picLabel);
			remove(jsc);
			validate();
			repaint();
			infoFlag = false;
		}

	}

	/***
	 * help function for food button
	 */
	private void foodBtn(){
		if (worm_single == null)
		{
			try {
				//Adding a picture of a worm
				picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Caterpie-icon.png"))));
				add(picLabel,BorderLayout.CENTER);
				this.validate();
				//repaint();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
			Singleton.set();
			worm_single = Singleton.getInstance();
		}
	}

	/***
	 * help function for reset button
	 */
	private void resetBtn(){
		//delete the swim set
		for(Swimmable i: swimSet){
			i.RemoveListen();
			i = null;
		}
		swimSet.removeAll(swimSet);
		//delete the immobile set
		immobileSet = new HashSet<Immobile>();
		swimSet = new HashSet<Swimmable>();
		repaint();

		if(worm_single != null)
			eatWorm();

		infoFlag = false;
	}

	/***
	 * help function for add animal button
	 */
	private void addAnimalBtn(){
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

	/***
	 * preform action when there is hungry fish
	 * @param s - hungry fish
	 */
	public void actionHungryFish(Swimmable s)
	{
		HungerState hs = new Hungry();
		hs.ChangeState(s);

		if (!is_worm()&&s!=null)
			JOptionPane.showMessageDialog(null, "Animal: " + s.getAnimalName() + "\nID:" +
					s.getAnimalID() + "\nFeed it using the 'Food' button", "Your animal is hungry!",
					JOptionPane.INFORMATION_MESSAGE);
	}

	//Get function for swim set and immobile Set
	public int getSwimSetSize() {return swimSet.size();}
	public HashSet<Immobile> getImmobileSet() {return immobileSet;}
	public  int getImmobileSetSize() {return  immobileSet.size();}
	public HashSet<Swimmable> getSwimSet(){return swimSet;}
}