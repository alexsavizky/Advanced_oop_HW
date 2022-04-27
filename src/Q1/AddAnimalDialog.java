package Q1;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class AddAnimalDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private ImageIcon img2 = new ImageIcon("src/whale.png");

	private final JPanel contentPane = new JPanel();
	private final JPanel controls = new JPanel();
	private final JPanel head = new JPanel();
	private GridLayout experimentLayout, experimentLayout2, experimentLayout3;
	
	private JButton confirmButton, cancelButton;
	
	private JComboBox<String> animalBox;
	private JComboBox<String> colorBox;
	
	private JLabel animalLabel, sizeLabel, horSpeedLabel, verSpeedLabel , colorLabel;
	
	private JTextField sizetxt, verSpeedtxt, horSpeedtxt;
	
	private String[] swims = {"Fish","Jellyfish"};  
	private String[] colors = {"Black","Red","Blue","Green","Cyan","Orange", "Yellow", "Magneta", "Pink"};
	private AquaPanel ap;

	

	

	/**
	 * Create the dialog.
	 */
	public AddAnimalDialog(AquaPanel ap) 
	{
		this.ap = ap;
		//setSize(450, 245);
		setSize(450, 305);
		setLayout(new BorderLayout());
		this.setTitle("Add Animal Dialog");
		this.setIconImage(img2.getImage());
		this.setLocationRelativeTo(null);
		
		MakeButtons();
		confirmButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		add(head, BorderLayout.NORTH);
        add(contentPane, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
	}
	
	public void MakeButtons() 
	{
		experimentLayout = new GridLayout(0,2);
		contentPane.setLayout(experimentLayout);
		
		experimentLayout2 = new GridLayout(0,2);
		controls.setLayout(experimentLayout2);
		
		experimentLayout3 = new GridLayout();
		head.setLayout(experimentLayout3);
		
//		int c = 8594;
		int c = 9728;
//		int c = 10031;
		
		JPanel matteBorders = new JPanel();
		TitledBorder titled = BorderFactory.createTitledBorder("Add a new animal");
		titled.setTitleFont(getFont());
		
        addCompForTitledBorder(titled,
                "You can add animals to your aquarium",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                matteBorders);
		
		String s = Character.toString((char)c);
		
		animalLabel = new JLabel(" " + s + "   Choose an animal:");
		animalBox = new JComboBox<String>(swims);
  		
		
		sizeLabel = new JLabel(" " + s + "   Animal's size (20-320):");
		sizetxt = new JTextField();
		
		horSpeedLabel = new JLabel(" " + s + "   Horizontal speed (1-10):");
		horSpeedtxt = new JTextField();
		
		verSpeedLabel = new JLabel(" " + s + "   Vertical speed  (1-10):");
		verSpeedtxt = new JTextField();
		
		colorLabel = new JLabel(" " + s + "   Pick a color:");
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
		
		//contentPane.setBorder(title);
		
		confirmButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		
		controls.add(confirmButton);
		controls.add(cancelButton);
		
		
		//controls.add(titledBorders);
		head.add(matteBorders);
		
        experimentLayout.setHgap(2);
        experimentLayout.setVgap(10);
	}

	public void GetFromDialog() 
	{
		Color color = Color.black;
		int size, h, v;
		String fishorjelly, colorname;
		try 
		{
			fishorjelly = animalBox.getItemAt(animalBox.getSelectedIndex());
			colorname = colorBox.getItemAt(colorBox.getSelectedIndex());
			size = Integer.parseInt(sizetxt.getText());
			h = Integer.parseInt(horSpeedtxt.getText());
			v = Integer.parseInt(verSpeedtxt.getText());
			
			if (size>320 || size<20)
				throw new Exception("Size must be between 20-320");
			if (h<1 || v<1 || h>10 || v>10)
				throw new Exception("All speeds must be between 1-10");
			
			if(colorname == "Black")
				color = Color.black;
			else if(colorname == "Red")
				color = Color.red;
			else if(colorname == "Blue")
				color = Color.blue;
			else if(colorname == "Green")
				color = Color.green;
			else if(colorname == "Cyan")
				color = Color.cyan;
			else if(colorname == "Orange")
				color = Color.orange;
			else if(colorname == "Yellow")
				color = Color.yellow;
			else if(colorname == "Magneta")
				color = Color.magenta;
			else if(colorname == "Pink")
				color = Color.pink;
			
			if (fishorjelly == "Fish") {
				ap.addAnimal(new Fish(ap, size, 200, 100, h, v, color));
				ap.infoFlag = false;
			}
			else if (fishorjelly == "Jellyfish") {
				ap.addAnimal(new Jellyfish(ap, size, 200, 100, h, v, color));
				ap.infoFlag = false;
			}
			dispose();
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
			}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == cancelButton)
			dispose();
		else if (e.getSource() == confirmButton)
			this.GetFromDialog();

	}
    
    void addCompForBorder(Border border,
            String description,
            Container container) {
		JPanel comp = new JPanel(new GridLayout(1, 1), false);
		JLabel label = new JLabel(description, JLabel.CENTER);
		comp.add(label);
		comp.setBorder(border);
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		container.add(comp);
    }
	
    void addCompForTitledBorder(TitledBorder border,
            String description,
            int justification,
            int position,
            Container container) {
				border.setTitleJustification(justification);
				border.setTitlePosition(position);
				addCompForBorder(border, description,
				     container);
    }
}
