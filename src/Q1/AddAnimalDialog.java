package Q1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddAnimalDialog extends JDialog implements ActionListener {

	private final JPanel contentPane = new JPanel();
	private JComboBox<String> animalsBox;
	private JComboBox<String> colorBox;
	private String[] swims = {"Fish","Jellyfish"};  
	private String[] colors = {"Black","Red","Blue","Green","Cyan","Orange", "Yellow", "Magneta", "Pink"};
	private JLabel animalLabel, sizeLabel, horSpeedLabel, verSpeedLabel , colorLabel;
	private JTextField sizetxt, verSpeedtxt, horSpeedtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddAnimalDialog dialog = new AddAnimalDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddAnimalDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setLayout(new FlowLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
