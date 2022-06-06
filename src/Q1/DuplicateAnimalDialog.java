package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

public class DuplicateAnimalDialog extends JDialog implements ActionListener {
    private AquaPanel ap;
    private String typeAnimal=null,colorType=null;
    private Color clr = null;
    private String[] swims;
    private int[] animals_id;
    private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types
    private JLabel animalLabel;
    private JComboBox<String> animalBox;
    private GridLayout experimentLayout;

    private AquaPanel panel;

    private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
    private Iterator<Swimmable> itrAnimals;
    private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
    private JButton b1,b2;
    private final JPanel contentPane = new JPanel();
    public DuplicateAnimalDialog(AquaPanel ap)
    {
        super();
        //Starting the dialog
        this.ap = ap;
        setSize(450, 305);
        setLayout(new BorderLayout());
        this.setTitle("Duplicate Animal Dialog");
        this.setLocationRelativeTo(null);
        //init swims
        int i =0;
        swims = new String[ap.getSwimSet().size()];
        for(Swimmable temp : ap.getSwimSet()){
            if(temp.getClass() ==Fish.class){
                swims[i] = "Fish " + temp.getAnimalID();
            }

            else {
                swims[i] = "Jellyfish " + temp.getAnimalID();
            }
            i++;
        }

        this.panel=panel;
        this.animalsSet= ap.getSwimSet();
        this.typeAnimal=null;

        setPanel();

        add(DialogPanel); // add the panel to the JDialog dialogTable 
        setVisible(true);// show the dialog
    }

    private void setPanel() {
        experimentLayout = new GridLayout(0,2);
        contentPane.setLayout(experimentLayout);

        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        animalLabel = new JLabel("Choose an animal:");
        animalBox = new JComboBox<String>(swims);


        contentPane.add(animalLabel);
        contentPane.add(animalBox);
        add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(experimentLayout);
        b1=new JButton("Duplicate");
        b2=new JButton("Cancel");

        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.setVisible(true);
        DialogPanel.add(contentPane,BorderLayout.CENTER);
        DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
        b1.addActionListener(this);
        b2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String animal= animalBox.getItemAt(animalBox.getSelectedIndex());
        int id_of_animal = animal.charAt(animal.length()-1)-48;
        if (e.getSource() == b2)
            dispose();
        else if(e.getSource() ==b1){
            {
                try
                {
                    if(ap.getSwimSetSize()>4)
                        throw new Exception("The maximum number of animals is 5");

                    for(Swimmable temp : ap.getSwimSet()){
                        if (temp.getAnimalID() == id_of_animal){
                            ap.addAnimal(temp.clone());
                            UpdateDuplicateAnimal a = new UpdateDuplicateAnimal(temp);
                            a.setVisible(true);
                            dispose();
                        }
                    }

                }
                catch(Exception e1){
                    if (e1.getMessage()!=null)
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            }

        }
    }

}

