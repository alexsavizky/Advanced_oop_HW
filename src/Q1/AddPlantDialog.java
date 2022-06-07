package Q1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

public class AddPlantDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    //Icon for dialog
    private ImageIcon img2 = new ImageIcon("src/seaweed.png");

    //Adding buttons, labels and boxes
    private final JPanel contentPane = new JPanel();
    private final JPanel controls = new JPanel();
    private final JPanel head = new JPanel();
    private JComboBox<String> plantBox;
    private JButton confirmButton, cancelButton;
    private JLabel plantLabel, sizeLabel;
    private JTextField sizetxt;
    //Gridlayouts for dialog
    private GridLayout experimentLayout, experimentLayout2, experimentLayout3;
    //List of choices for ComboBoxes
    private String[] plants = {"Laminaria","Zostera"};

    // AquaPanel (the main panel get by reference)
    private AquaPanel ap;
    private AbstractSeaFactory asf;
    private SeaCreature creature;
    protected Random rand;

    /***
     * Constructor
     * @param ap - main panel passed by reference
     */
    public AddPlantDialog(AquaPanel ap)
    {
        //Starting the dialog
        this.ap = ap;
        setSize(450, 205);
        setLayout(new BorderLayout());
        this.setTitle("Add Plant Dialog");
        this.setIconImage(img2.getImage());
        this.setLocationRelativeTo(null);

        //Create buttons
        MakeButtons();

        //Adding action listeners
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        //Locating the panels
        add(head, BorderLayout.NORTH);
        add(contentPane, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }

    /***
     *Make the buttons on the panel
     */
    public void MakeButtons()
    {
        experimentLayout = new GridLayout(0,2);
        contentPane.setLayout(experimentLayout);

        experimentLayout2 = new GridLayout(0,2);
        controls.setLayout(experimentLayout2);

        experimentLayout3 = new GridLayout();
        head.setLayout(experimentLayout3);

        int c = 9728;

        JPanel matteBorders = new JPanel();

        //Titled border header
        TitledBorder titled = BorderFactory.createTitledBorder("Add a new plant");
        titled.setTitleFont(getFont());
        addCompForTitledBorder(titled,
                "You can add plants to your aquarium",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                matteBorders);

        String s = Character.toString((char)c);


        //Adding Labels and creating a field for each one
        plantLabel = new JLabel(" " + s + "   Choose a plant:");
        plantBox = new JComboBox<String>(plants);

        sizeLabel = new JLabel(" " + s + "   Plant's size (100-400):");
        sizetxt = new JTextField();

        //Adding the labels and fields to contentPane
        contentPane.add(plantLabel);
        contentPane.add(plantBox);

        contentPane.add(sizeLabel);
        contentPane.add(sizetxt);

        //Creating Confirm and Cancel buttons
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        //Adding the buttons to controls
        controls.add(confirmButton);
        controls.add(cancelButton);

        //Adding the titled border
        head.add(matteBorders);

        //Setting gaps
        experimentLayout.setHgap(2);
        experimentLayout.setVgap(10);

    }

    /***
     * create plant in the aqua panel according to the info in this dialog
     */
    public void createPlant()
    {
        int size;
        String plantname;
        try
        {
            //initialize the info from this dialog
            plantname = plantBox.getItemAt(plantBox.getSelectedIndex());
            size = Integer.parseInt(sizetxt.getText());

            //Checking for legit parameters
            if (size>400 || size<100)
                throw new Exception("Size must be between 100-400");

            //Random spawn for plants
            rand = new Random();
            int xx = rand.nextInt(ap.getWidth());

            //Adding a plant
            if (plantname == "Laminaria")
            {
                asf = new PlantFactory(ap, size, xx, ap.getHeight() - 30);
                creature = asf.produceSeaCreature("Laminaria");
                ap.addPlant((Immobile)creature);
            }
            else if (plantname == "Zostera")
            {
                asf = new PlantFactory(ap, size, xx, ap.getHeight() - 30);
                creature = asf.produceSeaCreature("Zostera");
                ap.addPlant((Immobile)creature);
            }
            dispose();
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    /***
     * preform the buttons functionality
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == cancelButton)
            dispose();
        else if (e.getSource() == confirmButton)
        {
            try
            {
                if(ap.getImmobileSetSize()>4)
                    throw new Exception("The maximum number of plants is 5");
                this.createPlant();
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        }
    }

    /***
     *addCompForBorder - styling header
     */
    void addCompForBorder(Border border,
                          String description,
                          Container container)
    {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        JLabel label = new JLabel(description, JLabel.CENTER);
        comp.add(label);
        comp.setBorder(border);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }

    /***
     *addCompForTitledBorder - styling header
     */
    void addCompForTitledBorder(TitledBorder border,
                                String description,
                                int justification,
                                int position,
                                Container container)
    {
        border.setTitleJustification(justification);
        border.setTitlePosition(position);
        addCompForBorder(border, description,
                container);
    }

}
