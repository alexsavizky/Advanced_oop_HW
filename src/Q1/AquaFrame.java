package Q1;

import java.awt.*;  
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.io.File;
import java.io.IOException;

public class AquaFrame extends JFrame implements ActionListener {

	private AquaPanel ap;
	private JMenuBar ElMenu;
	private JMenu file ,background ,help;
	private JMenuItem exit, image, blue, none, helpz;
	private BufferedImage img = null;

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

	
	public void loadImage()
    {
//        if(lb_background!=null)
//            panel.removeAll();
 
        FileDialog fd=new FileDialog(new Frame(),"please choose a file:",FileDialog.LOAD);
        fd.setVisible(true);
        File f;
        if(fd.getFile()!=null)
        {
            f=new File(fd.getDirectory(),fd.getFile());
            try{
               // ImageIcon a=new ImageIcon(ImageIO.read(f));
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
	
	
	//This is an override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == exit)
			System.exit(0);
		
		else if (e.getSource() == image)
			loadImage();
		
		else if (e.getSource() == none)
			ap.setBackground(Color.white);
		
		else if (e.getSource() == blue)
			ap.setBackground(Color.blue);
		
		else if (e.getSource() == helpz)
			JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads");
	}

}
