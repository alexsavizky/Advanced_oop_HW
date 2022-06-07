/*	 Authors:
 *   Bar Shwartz - 313162265
 *   Alex Savitzky - 316611409
 */

package Q1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AquaFrame extends JFrame implements ActionListener 
{

	private static final long serialVersionUID = 1L;

	private ImageIcon img = new ImageIcon("src/aquarium.png");	//Icon for aqua frame

	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();	//size for frame
	
	//Panel for use
	private AquaPanel ap;
	private SaveStateDialog save_dialog;
	private  RestoreStateDialog restore_dialog;
	
	//Parts of menu
	private JMenuBar ElMenu;
	private JMenu file ,background ,help,memento;
	private JMenuItem exit, image, blue, none, helpz,save,restore;
	//memento functionality
	private Caretaker caretaker = new Caretaker();
	private Originator originator = new Originator();

	/***
	 *main func of the project initialize the aqua frame
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

	/***
	 * Constructor
	 */
	public AquaFrame() 
	{
		//Starting the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int)size.getWidth() - 20, 600);
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
		save.addActionListener(this);
		restore.addActionListener(this);
	}

	/***
	 * make sub menus and buttons
	 */
	private void MakeMenu()
	{
		//Creating choices and menu items
		ElMenu = new JMenuBar();

		//create sub menus
		file = new JMenu("File");
		background = new JMenu("Background");
		memento = new JMenu("Memento");
		help = new JMenu("Help");

		//create buttons
		exit = new JMenuItem("Exit");
		image = new JMenuItem("Image");
		blue = new JMenuItem("Blue");
		none = new JMenuItem("None");
		helpz = new JMenuItem("Help");
		save = new JMenuItem("Save Object State");
		restore = new JMenuItem("Restore Object State");

		//arrange the buttons ant the sub menus
		file.add(exit);
		background.add(image);
		background.add(blue);
		background.add(none);
		help.add(helpz);
		memento.add(save);
		memento.add(restore);

		//Adding choices to menu
		ElMenu.add(file);
		ElMenu.add(background);
		ElMenu.add(memento);
		ElMenu.add(help);
		setJMenuBar(ElMenu);
	}

	/***
	 * Load an image for background
	 */
	private void LoadImage()
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

	/***
	 * preform the buttons functionality
	 * @param e the event to be processed
	 */
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
			none();

		//CLICK "save"
		else if(e.getSource() == save){
			save_dialog = new SaveStateDialog(ap,caretaker,originator);
			save_dialog.setVisible(true);
		}

		//CLICK "restore"
		else if(e.getSource() == restore){
			restore_dialog = new RestoreStateDialog(ap,caretaker,originator);
			restore_dialog.setVisible(true);
		}
		
		//CLICK "Blue"
		else if (e.getSource() == blue)
			blue();

		//CLICK "Help"
		else if (e.getSource() == helpz)
			JOptionPane.showMessageDialog(null, "Home Work 3\n GUI @ Threads");
	}

	/***
	 * help function for none button
	 */
	private void none(){
		if (ap.background != null)
		{
			ap.background = null;
			ap.repaint();
		}
		ap.setBackground(Color.white);
	}

	/***
	 * help function for blue button
	 */
	private void blue(){
		if (ap.background != null)
		{
			ap.background = null;
			ap.repaint();
		}
		ap.setBackground(Color.blue);
	}
}
