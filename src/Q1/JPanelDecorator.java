package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JColorChooser;

public class JPanelDecorator extends JPanel implements ActionListener
{
    private JColorChooser colorChooser;
    private JPanel contentPane;
    private JPanel buttons;
    private JButton changeColorButton;
    private JLabel lbl;
    private JDialog decoratorDialog;
    private JComboBox<String> animalBox;
    private String animalFromBox;
    private Color currCol;

    private HashSet<Swimmable> swimSet;
    private Iterator<Swimmable> itrAnimals;

    private String[] swims;

    private AquaPanel ap;


    public JPanelDecorator(AquaPanel ap)
    {
        super();
        this.ap = ap;
        this.swimSet = ap.getSwimSet();

        setLayout(new BorderLayout());

        //--- Making a Change Color button ---
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(0,1,0,0));
        changeColorButton = new JButton("Change Color");
        buttons.add(changeColorButton);
        add(buttons,BorderLayout.SOUTH);

        //Adding listener to button
        changeColorButton.addActionListener(this);

        //--- Displaying and choosing a fish to change color ---
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0,2,0,0));

        lbl = new JLabel("  Choose an existing animal:");
        contentPane.add(lbl);

        int i = 0;
        swims = new String[ap.getSwimSet().size()];
        for(Swimmable temp : ap.getSwimSet())
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



        add(contentPane,BorderLayout.CENTER);


        decoratorDialog = new JDialog();
        decoratorDialog.setSize(500, 145);
        decoratorDialog.setLayout(new BorderLayout());
        decoratorDialog.setTitle("JPanel Decorator");
        decoratorDialog.setLocationRelativeTo(null);

        decoratorDialog.add(this);

        decoratorDialog.setVisible(true);

//        setLayout(new BorderLayout());
//        setBackground(Color.white);

    }



    public void actionPerformed(ActionEvent e)
    {

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

    public void GetFromBox()
    {
        animalFromBox = animalBox.getItemAt(animalBox.getSelectedIndex());
        int id_of_animal = animalFromBox.charAt(animalFromBox.length()-1)-48;
        MarineAnimal s = getTheID(id_of_animal, swimSet);

        currCol = getObjectCol(id_of_animal, swimSet);

        System.out.println(currCol.toString());

        try
        {
            colorChooser = new JColorChooser();
            Color col = JColorChooser.showDialog(null, "Choose a color", currCol);
            if (col!=null)
                s.PaintFish(col);
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }

    }

    public MarineAnimal getTheID(int id, HashSet<Swimmable> swimSet)
    {
        itrAnimals = swimSet.iterator();
        while(itrAnimals.hasNext())
        {
            Swimmable s = itrAnimals.next();
            if(s.getAnimalID() == id)
            {
                if(s.getAnimalName() == "Fish")
                    return (Fish)s;
                else if(s.getAnimalName() == "Jellyfish")
                    return (Jellyfish)s;
            }
        }
        return null;
    }

    public Color getObjectCol(int id, HashSet<Swimmable> swimSet)
    {
        itrAnimals = swimSet.iterator();
        while(itrAnimals.hasNext())
        {
            Swimmable s = itrAnimals.next();
            if(s.getAnimalID() == id)
                return s.getCol();
        }
        return null;
    }
}
