package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DuplicateAnimalDialog extends JDialog implements ActionListener {
    private AquaPanel ap;// AquaPanel (the main panel get by reference)
    private String[] swims; //List of choices for ComboBoxes

    //Adding buttons, labels and boxes
    private JLabel animalLabel;
    private JComboBox<String> animalBox;
    private JButton b1,b2;
    private GridLayout experimentLayout;
    private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
    private final JPanel contentPane = new JPanel();

    /***
     * Constructor
     * @param ap - main panel passed by reference
     */
    public DuplicateAnimalDialog(AquaPanel ap)
    {
        super();
        //Starting the dialog
        this.ap = ap;
        setSize(450, 105);
        setLayout(new BorderLayout());
        this.setTitle("Duplicate Animal Dialog");
        this.setLocationRelativeTo(null);


        //init swims list in format animal name + id
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

        setPanel();

        add(DialogPanel); // add the panel to the JDialog dialogTable 
        setVisible(true);// show the dialog
    }

    /***
     * makes the buttons and the sub menus
     */
    private void setPanel() {
        experimentLayout = new GridLayout(0,2);
        contentPane.setLayout(experimentLayout);

        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        animalLabel = new JLabel("  Choose an animal:");
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

    /***
     * preform the buttons functionality
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        //CLICK "Cancel" - b2
        if (e.getSource() == b2)
            this.dispose();
        //CLICK "Duplicate" - b1
        else if(e.getSource() ==b1)
            duplicate();
    }

    /***
     * help function for duplicate button
     */
    private void duplicate(){
        {
            try
            {
                if(ap.getSwimSetSize()>4)
                    throw new Exception("The maximum number of animals is 5");
                String animal= animalBox.getItemAt(animalBox.getSelectedIndex());
                int id_of_animal = animal.charAt(animal.length()-1)-48;

                //look for the right animal
                for(Swimmable temp : ap.getSwimSet()){
                    if (temp.getAnimalID() == id_of_animal){
                        //clone the animal
                        Swimmable s = temp.clone();
                        ap.addAnimal(s);
                        UpdateDuplicateAnimalDialog a = new UpdateDuplicateAnimalDialog(s);
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

