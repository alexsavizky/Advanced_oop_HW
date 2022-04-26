package Q1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddAnimalDialog extends JDialog implements ActionListener {
	
	private ImageIcon img2 = new ImageIcon("src/whale.png");
	private final JPanel contentPane = new JPanel();
	private final JPanel controls = new JPanel();
	private GridLayout experimentLayout, experimentLayout2;
	
	private JButton confirmButton, cancelButton;
	
	private JComboBox<String> animalBox;
	private JComboBox<String> colorBox;
	
	private JLabel animalLabel, sizeLabel, horSpeedLabel, verSpeedLabel , colorLabel;
	
	private JTextField sizetxt, verSpeedtxt, horSpeedtxt;
	
	private String[] swims = {"Fish","Jellyfish"};  
	private String[] colors = {"Black","Red","Blue","Green","Cyan","Orange", "Yellow", "Magneta", "Pink"};
	private AquaPanel pan;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddAnimalDialog dialog = new AddAnimalDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddAnimalDialog(AquaPanel pan) 
	{
		this.pan = pan;
		setSize(450, 245);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		this.setTitle("Add Animal Dialog");
		this.setIconImage(img2.getImage());
		this.setLocationRelativeTo(null);
		
		MakeButtons();
		confirmButton.addActionListener(this);
		cancelButton.addActionListener(this);
		  
        add(contentPane, BorderLayout.NORTH);
        add(controls, BorderLayout.SOUTH);
	}
	
	public void MakeButtons() 
	{
		experimentLayout = new GridLayout(0,2);
		contentPane.setLayout(experimentLayout);
		
		experimentLayout2 = new GridLayout(0,2);
		controls.setLayout(experimentLayout2);

		animalLabel = new JLabel(" Choose an animal:");
		animalBox = new JComboBox<String>(swims);
		
		sizeLabel = new JLabel(" Animal's size:");
		sizetxt = new JTextField();
		
		horSpeedLabel = new JLabel(" Horizontal speed (1-10):");
		horSpeedtxt = new JTextField();
		
		verSpeedLabel = new JLabel(" Vertical speed  (1-10):");
		verSpeedtxt = new JTextField();
		
		colorLabel = new JLabel(" Pick a color:");
		colorBox = new JComboBox<String>(colors);
		
		contentPane.add(animalLabel);
		contentPane.add(animalBox);
		
		contentPane.add(sizeLabel);
		contentPane.add(sizetxt);
		
		contentPane.add(horSpeedLabel);
		contentPane.add(horSpeedtxt);
		
		contentPane.add(verSpeedLabel);
		contentPane.add(verSpeedtxt);
		
		contentPane.add(colorLabel);
		contentPane.add(colorBox);
		
		confirmButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		
		controls.add(confirmButton);
		controls.add(cancelButton);
		
        experimentLayout.setHgap(2);
        experimentLayout.setVgap(10);
        
        experimentLayout2.setHgap(0);
	}

//	public void GetInfoFromDialog() 
//	{
//		Color color;
//		int size, h, v;
//		String fishorjelly, colorname;
//		try 
//		{
//			fishorjelly = animalBox.getItemAt(animalBox.getSelectedIndex());
//			
//		}
//	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == cancelButton)
			dispose();
		else 
		{
			
		}
		
	}

}
