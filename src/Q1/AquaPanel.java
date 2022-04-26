package Q1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AquaPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel buttons;
	private AddAnimalDialog aad;
	private JButton b1, b2, b3, b4, b5, b6, b7;
	protected Image background = null;
	private HashSet<Swimmable> swimSet = new HashSet<Swimmable>();
	
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
			aad = new AddAnimalDialog();
			aad.setVisible(true);
		}
		else if (e.getSource() == b7)
			System.exit(0);
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
        Graphics2D G = (Graphics2D) g;
        G.drawImage(this.background,0,0,getWidth(),getHeight(),this);
	}


}
