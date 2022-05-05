package Q1;

import java.awt.*;  
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;


public class AquaFrame extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	//Icon for frame
	private ImageIcon img = new ImageIcon("src/aquarium.png");
	
	//Panel for use
	private AquaPanel ap;
	
	//Parts of menu
	private JMenuBar ElMenu;
	private JMenu file ,background ,help;
	private JMenuItem exit, image, blue, none, helpz;

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

	//Default constructor
	public AquaFrame() 
	{
		
		//Starting the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 600);
		this.setTitle("Alex & Bar's Aquarium");
		this.setIconImage(img.getImage());
		setLocationRelativeTo(null);

		//Adding AquaPanel
		ap = new AquaPanel();
		add(ap);
		
		//Making the menu
		MakeMenu();
		
		//Adding action listeners
		exit.addActionListener(this);
		image.addActionListener(this);
		blue.addActionListener(this);
		helpz.addActionListener(this);
		none.addActionListener(this);
	}
	
	//Make the menu function
	public void MakeMenu() 
	{
		//Creating choices and menu items
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
		
		//Adding choices to menu
		ElMenu.add(file);
		ElMenu.add(background);
		ElMenu.add(help);
		
		setJMenuBar(ElMenu);
	}

	//Load an image for background
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
	
	//Frame menu functionality
	public void actionPerformed(ActionEvent e) 
	{
		//CLICK "Exit"
		if (e.getSource() == exit)
			System.exit(0);
		
		//CLICK "Image"
		else if (e.getSource() == image)
			LoadImage();
		
		//CLICK "None"
		else if (e.getSource() == none) 
		{
			if (ap.background != null)
			{
				ap.background = null;
				ap.repaint();
			}
			ap.setBackground(Color.white);
		}
		
		//CLICK "Blue"
		else if (e.getSource() == blue) 
		{
			if (ap.background != null)
			{
				ap.background = null;
				ap.repaint();
			}
			ap.setBackground(Color.blue);
		}

		//CLICK "Help"
		else if (e.getSource() == helpz)
			JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads");
	}
}
