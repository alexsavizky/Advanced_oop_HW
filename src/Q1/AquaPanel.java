package Q1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;



public class AquaPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel buttons;
	private AddAnimalDialog aad;
	private JButton b1, b2, b3, b4, b5, b6, b7;
	protected Image background = null;
	private HashSet<Swimmable> swimSet = new HashSet<Swimmable>();
	private Iterator <Swimmable>itrAnimals;
	private JLabel picLabel;
	private Boolean worm = false;
	
	private String[] columnNames = { "Animal", "Color", "Size", "Hor. Speed", "Ver. Speed", "Eat counter"};
	private String[][] data = new String[6][6];
	private JTable jt;
	
	/**
	 * Create the panel.
	 */
	public AquaPanel() 
	{
		super();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		MakeButtons();

	}
	
	public void MakeButtons() 
	{
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(0,7,0,0));
		b1 = new JButton("Add Animal");
        b2 = new JButton("Sleep");
        b3 = new JButton("Wake up");
        b4 = new JButton("Reset");
        b5 = new JButton("Food");
        b6 = new JButton("Info");
        b7 = new JButton("Exit");
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		buttons.add(b5);
		buttons.add(b6);
		buttons.add(b7);
		add(buttons,BorderLayout.SOUTH);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);

		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == b1) 
		{
			aad = new AddAnimalDialog(this);
			aad.setVisible(true);
			//addAnimal(new Jellyfish(this,100,200,100,10,10,Color.magenta));
		}
		else if(e.getSource()== b2) {
			itrAnimals= swimSet.iterator(); 
		 	while(itrAnimals.hasNext()){
		 		itrAnimals.next().setSuspend(); 
		 	}
		}
		else if(e.getSource()== b3) {
			itrAnimals= swimSet.iterator(); 
		 	while(itrAnimals.hasNext()){
		 		itrAnimals.next().setResume(); 
		 	}
		}

		
		else if(e.getSource() == b5) {
			worm = true;
			try {
				picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("src/Caterpie-icon.png"))));
				add(picLabel);
				this.validate();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource() == b6) 
		{
			String name, color;
			int size, h, v, food, i = 0, all = 0;
			itrAnimals = swimSet.iterator();
			Swimmable s;
			
			while(itrAnimals.hasNext()){
//		 		name = itrAnimals.next().getAnimalName();
//		 		color = itrAnimals.next().getColor();
//		 		size = itrAnimals.next().getSize();
//		 		h = itrAnimals.next().getHorSpeed();
//		 		v = itrAnimals.next().getVerSpeed();
//		 		food = 0;
//		 		data += {name, color, size, h, v, food};
				s = itrAnimals.next();
		 		data[i][0] = s.getAnimalName();
		 		data[i][1] = s.getColor();
		 		data[i][2] = String.valueOf(s.getSize());
		 		data[i][3] = String.valueOf(s.getHorSpeed());
		 		data[i][4] = String.valueOf(s.getVerSpeed());
		 		data[i][5] = String.valueOf(0);
		 		all += s.getEatCount();
		 		i++;
		 		
	        }
			data[i][0] = "Total";
			data[i][5] = String.valueOf(all);
			
			jt = new JTable(data, columnNames);
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			add(jt);
			this.validate();
			//jt.setVisible(true);
		}
		
		else if (e.getSource() == b7)
			System.exit(0);

	}

	public void paintComponent(Graphics g) 
	{
		
		super.paintComponent(g);
        Graphics2D G = (Graphics2D) g;
        G.drawImage(this.background,0,0,getWidth(),getHeight(),this);
        itrAnimals= swimSet.iterator(); //intialzie iterator 
        while(itrAnimals.hasNext()){
	 		itrAnimals.next().drawAnimal(g);
        }
	}
	
	
	public void addAnimal(Swimmable swim) {
		//swim.addObserver(this);
		swimSet.add(swim);
		repaint();
		swim.start();
	}
	
	
			
			//Fish new_fish = new Fish(this,100,200,100,10,10,Color.magenta);
			
			//new_fish.drawAnimal(getGraphics());
			//new_fish.start();
			//for(int i =0; i<10 ; i++)
			//{
				//new_fish.drawAnimal(getGraphics());
				
				//new_fish.start();
					
				
				//this.repaint();
			//}
		
	


}
