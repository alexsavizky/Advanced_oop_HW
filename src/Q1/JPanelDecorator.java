package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JColorChooser;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class JPanelDecorator extends JPanel implements ActionListener
{
    private JColorChooser colorChooser;                                 //JColorChooser for desired color
    private JPanel contentPane, buttons;                                //Panels for buttons and content
    private JButton changeColorButton;                                  //Button
    private JLabel lbl;                                                 //Label
    private JDialog decoratorDialog;                                    //Dialog
    private JComboBox<String> animalBox;                                //ComboBox
    private String animalFromBox;                                       //Chosen animal from ComboBox
    private Color currCol;                                              //Current color
    private HashSet<Swimmable> swimSet;                                 //HashSet of Swimmables


    private String[] swims;                                             //List of strings
    private AquaPanel ap;                                               //AquaPanel object
    private ImageIcon img3 = new ImageIcon("nicefish.png"); //Icon for panel
    private MarineAnimalDecorator mad;                                  //MarineAnimalDecorator object

    /***
     * Constructor
     * @param ap - Main panel passed by reference
     */
    public JPanelDecorator(AquaPanel ap)
    {
        super();
        this.ap = ap;
        this.swimSet = ap.getSwimSet();

        setLayout(new BorderLayout());

        //Making a Change Color button ---> buttons
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(0,1,0,0));
        changeColorButton = new JButton("Change Color");
        buttons.add(changeColorButton);
        //Adding listener to button
        changeColorButton.addActionListener(this);


        //Displaying and choosing a fish to change color ---> contentPane
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0,2,0,0));
        lbl = new JLabel("  Choose an existing animal:");
        contentPane.add(lbl);

        int i = 0;

        //Info about swimmables for contentPane
        swims = new String[ap.getSwimSet().size()];
        for(Swimmable temp : this.ap.getSwimSet())
        {
            if(temp.getClass() == Fish.class){
                swims[i] = "Fish  |  Color: " + temp.getColor() + "  |  ID: " + temp.getAnimalID();
            }
            else {
                swims[i] = "Jellyfish  |  Color: " + temp.getColor() + "  |  ID: " + temp.getAnimalID();
            }
            i++;
        }
        animalBox = new JComboBox<>(swims);
        contentPane.add(animalBox);


        //Titled border header ---> matteBorders
        JPanel matteBorders = new JPanel();
        TitledBorder titled = BorderFactory.createTitledBorder("Decorate an animal");
        titled.setTitleFont(getFont());
        addCompForTitledBorder(titled,
                "You can change the color of your animals",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                matteBorders);

        //Locating content
        add(buttons,BorderLayout.SOUTH);
        add(contentPane,BorderLayout.CENTER);
        add(matteBorders,BorderLayout.NORTH);

        //Creating a dialog
        decoratorDialog = new JDialog();
        decoratorDialog.setSize(500, 145);
        decoratorDialog.setLayout(new BorderLayout());
        decoratorDialog.setTitle("Decorator");
        decoratorDialog.setLocationRelativeTo(null);
        decoratorDialog.setIconImage(img3.getImage());

        //Adding the panel to the new dialog
        decoratorDialog.add(this);

        decoratorDialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        //CLICK "Change Color"
        if (e.getSource() == changeColorButton)
        {
            try
            {
                if (swimSet.size() == 0)
                    throw new Exception("Must choose an animal");
                GetFromBox();
                decoratorDialog.dispose();
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        }
    }

    /***
     * Get info from dialog
     */
    public void GetFromBox()
    {
        //Getting the desired animal from the dialog
        animalFromBox = animalBox.getItemAt(animalBox.getSelectedIndex());
        int id_of_animal = animalFromBox.charAt(animalFromBox.length()-1)-48;
        MarineAnimal s = getFromID(id_of_animal, swimSet);

        //Saving the current color
        currCol = getObjectCol(id_of_animal, swimSet);

        try
        {
            colorChooser = new JColorChooser();
            Color col = JColorChooser.showDialog(null, "Choose a color", currCol);

            //If a color was chosen
            if (col!=null){
                mad = new MarineAnimalDecorator(s);
                mad.PaintFish(col);
            }
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    /***
     * Get an animal from a swimset using an ID
     * @param id - ID of desired animal
     * @param swimSet - HashSet of swimmables
     * @return Desired animal
     */
    public MarineAnimal getFromID(int id, HashSet<Swimmable> swimSet)
    {
        for(Swimmable i: swimSet)
        {
            if(i.getAnimalID() == id)
            {
                if(i.getAnimalName() == "Fish")
                    return (Fish)i;
                else if(i.getAnimalName() == "Jellyfish")
                    return (Jellyfish)i;
            }
        }
        return null;
    }

    /***
     * Get color of an animal from a swimset using an ID
     * @param id - ID of desired animal
     * @param swimSet - HashSet of swimmables
     * @return The desired animal color
     */
    public Color getObjectCol(int id, HashSet<Swimmable> swimSet)
    {
        for(Swimmable s : swimSet)
        {
            if(s.getAnimalID() == id)
                return s.getCol();
        }
        return null;
    }

    /***
     * Styling the header
     */
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
    /***
     * Styling the header
     */
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
