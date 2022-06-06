package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

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

    private HashSet<Swimmable> swimSet = new HashSet<Swimmable>();
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
            if(temp.getClass() ==Fish.class){
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



//        //Creating a JDialog to display the panel
//        decoratorDialog = new JDialog();
//        decoratorDialog.setSize(450, 255);
//        decoratorDialog.setLayout(new BorderLayout());
//        decoratorDialog.setTitle("JPanel Decorator");
//        decoratorDialog.setLocationRelativeTo(null);
//        decoratorDialog.add(this);
//        decoratorDialog.setVisible(true);




//        setSize(450, 305);
//        setLayout(new BorderLayout());
//        this.setTitle("Add Animal Dialog");
//        this.setIconImage(img2.getImage());
//        this.setLocationRelativeTo(null);

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
        MarineAnimal s = getObjectById(id_of_animal, swimSet);
        s.PaintFish(Color.red);
    }

    public MarineAnimal getObjectById(int idObject, HashSet<Swimmable> swimSet)
    {
        itrAnimals = swimSet.iterator();
        while(itrAnimals.hasNext())
        {
            Swimmable sw = itrAnimals.next();
            if(sw.getAnimalID()==idObject)
            {
                if(sw.getAnimalName()=="Fish")
                    return (Fish)sw;
                else if(sw.getAnimalName()=="Jellyfish")
                    return (Jellyfish)sw;
            }
        }
        return null;
    }
}
