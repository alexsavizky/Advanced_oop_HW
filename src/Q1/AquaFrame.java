package Q1;

import java.awt.*;  
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;
import Q1.*;


public class AquaFrame extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private AquaPanel ap;
	private JMenuBar ElMenu;
	private JMenu file ,background ,help;
	private JMenuItem exit, image, blue, none, helpz;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AquaFrame frame = new AquaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AquaFrame() 
	{
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 600);
		
		//Adding AquaPanel
		ap = new AquaPanel();
		add(ap);
		
		//Making the menu
		MakeMenu();
		
		exit.addActionListener(this);
		image.addActionListener(this);
		blue.addActionListener(this);
		helpz.addActionListener(this);
		none.addActionListener(this);

	}
	
	public void MakeMenu() 
	{
		ElMenu = new JMenuBar();
		file = new JMenu("File");
		background = new JMenu("Background");
		help = new JMenu("Help");
		
		exit = new JMenuItem("Exit");
		image = new JMenuItem("Image");
		blue = new JMenuItem("Blue");
		none = new JMenuItem("None");
		helpz = new JMenuItem("Help");
		
		file.add(exit);
		background.add(image);
		background.add(blue);
		background.add(none);
		help.add(helpz);
		
		ElMenu.add(file);
		ElMenu.add(background);
		ElMenu.add(help);
		
		setJMenuBar(ElMenu);
	}

	
	public void LoadImage()
    {
        FileDialog fd = new FileDialog(new Frame(),"Please choose a file:",FileDialog.LOAD);
        fd.setVisible(true);
        if(fd.getFile() != null)
        {
            try{
            	File f = new File(fd.getDirectory(),fd.getFile());
                ap.background = ImageIO.read(f);
                ap.repaint();
                setVisible(true);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == exit)
			System.exit(0);
		
		else if (e.getSource() == image)
		{
			
			LoadImage();
		}
		else if (e.getSource() == none) 
		{
			if (ap.background != null)
			{
				ap.background = null;
				ap.repaint();
			}
			ap.setBackground(Color.white);
		}
		
		else if (e.getSource() == blue) 
		{
			if (ap.background != null)
			{
				ap.background = null;
				ap.repaint();
			}
			ap.setBackground(Color.blue);
		}
//		else if(e.getSource() == ap.b1) {
//			Fish new_fish = new Fish(100,100,100,100,100,Color.magenta);
//			new_fish.drawAnimal(getGraphics());
//			ap.repaint();
//		}
//		else if (e.getSource() == ap.b2)
//			System.exit(0);
		else if (e.getSource() == helpz)
			JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads");
	}

}
