package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDuplicateAnimalDialog extends JDialog implements ActionListener {
    private String[] colors = {"Black","Red","Blue","Green","Cyan"      //List of colors
            ,"Orange", "Yellow", "Magneta", "Pink"};
    private AquaPanel ap;                                               //AquaPanel object
    private GridLayout experimentLayout;                                //GridLayout for gaps
    private JPanel contentPane;                                         //Panel for content
    private JLabel sizeLabel,horSpeedLabel,verSpeedLabel,colorLabel;    //Labels
    private JButton confirmButton, cancelButton;                        //Confirm and cancel buttons
    private JTextField sizetxt,horSpeedtxt,verSpeedtxt;                 //Text fields
    private Swimmable clone;                                            //Swimmable for cloning
    private JComboBox<String> colorBox;                                 //Color box for the color list

    /***
     * Creating the dialog
     * @param clone - Saving the clone for later use
     */
    public UpdateDuplicateAnimalDialog(Swimmable clone){
        //Basic dialog settings
        super();
        this.clone = clone;
        setSize(450, 305);
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        setTitle("Update Duplicate Animal");
        setLocationRelativeTo(null);

        //GridLayout for gaps in contentPane
        experimentLayout = new GridLayout(0,2);
        contentPane = new JPanel();
        contentPane.setLayout(experimentLayout);

        //special letter for styling
        int c = 9728;
        String s = Character.toString((char)c);

        //Basic settings for labels, textboxes and boxes
        sizeLabel = new JLabel(" " + s + "   Animal's size (20-320):");
        sizetxt = new JTextField();
        horSpeedLabel = new JLabel(" " + s + "   Horizontal speed (1-10):");
        horSpeedtxt = new JTextField();
        verSpeedLabel = new JLabel(" " + s + "   Vertical speed  (1-10):");
        verSpeedtxt = new JTextField();
        colorLabel = new JLabel(" " + s + "   Pick a color:");
        colorBox = new JComboBox<String>(colors);

        //Adding elements to contentPane
        contentPane.add(sizeLabel);
        contentPane.add(sizetxt);
        contentPane.add(horSpeedLabel);
        contentPane.add(horSpeedtxt);
        contentPane.add(verSpeedLabel);
        contentPane.add(verSpeedtxt);
        contentPane.add(colorLabel);
        contentPane.add(colorBox);

        //Basic settings for buttons
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        //Panel for controls
        JPanel controls = new JPanel();
        controls.add(confirmButton);
        controls.add(cancelButton);

        //Gaps
        experimentLayout.setHgap(2);
        experimentLayout.setVgap(10);

        //Adding listeners
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        //Adding the panels to the dialog
        add(contentPane, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }

    @Override
    /***
     * Functionality for buttons
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton)
            dispose();
        else if (e.getSource() == confirmButton)
            changeDuplicate();
    }
    public void changeDuplicate()
    {
        Color color = Color.black;
        int size, h, v;
        String colorname;
        try
        {
            colorname = colorBox.getItemAt(colorBox.getSelectedIndex());
            size = Integer.parseInt(sizetxt.getText());
            h = Integer.parseInt(horSpeedtxt.getText());
            v = Integer.parseInt(verSpeedtxt.getText());

            //Checking for legit parameters
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

            clone.SetClone(size,h,v,color);

            dispose();
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }
}