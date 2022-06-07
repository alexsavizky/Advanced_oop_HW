package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SaveStateDialog extends JDialog implements ActionListener {
    private AquaPanel ap;                               //AquaPanel object
    private String[] ceatures;                          //List of strings
    private JLabel animalLabel;                         //Label
    private JComboBox<String> animalBox;                //ComboBox to choose an animal
    private GridLayout experimentLayout;                //GridLayout for gaps
    private JPanel DialogPanel,buttonsPanel;            //Panels for the dialog
    private JButton b1,b2;                              //Buttons for dialog
    private Caretaker caretaker;                        //Manages the memento state
    private Originator originator ;                     //Saves the states into caretaker
    private JPanel contentPane = new JPanel();          //contentPane for dialog

    /***
     * @param ap - Main panel passed by reference
     * @param caretaker - Set the caretaker
     * @param originator - Set the originator
     */
    public SaveStateDialog(AquaPanel ap,Caretaker caretaker,Originator originator){
        super();

        //Starting the dialog
        this.ap = ap;
        this.caretaker =caretaker;
        this.originator = originator;
        setSize(450, 105);
        setLayout(new BorderLayout());
        this.setTitle("Save State Dialog");
        this.setLocationRelativeTo(null);

        //init swims
        int i =0;
        ceatures = new String[ap.getSwimSet().size()+ap.getImmobileSetSize()];
        for(Swimmable temp : ap.getSwimSet()){
            if(temp.getClass() ==Fish.class){
                ceatures[i] = "Fish " + temp.getAnimalID();
            }
            else if (temp.getClass() ==Jellyfish.class){
                ceatures[i] = "Jellyfish " + temp.getAnimalID();
            }
            i++;
        }
        for (Immobile temp: ap.getImmobileSet()){
            if(temp.getClass() ==Laminaria.class){
                ceatures[i] = "Laminaria " + temp.getPlantId();
            }

            else if(temp.getClass() ==Zostera.class){
                ceatures[i] = "Zostera " + temp.getPlantId();
            }
            i++;
        }
        setPanel();

        add(DialogPanel); // Add the panel to the JDialog dialogTable
        setVisible(true); // Show the dialog
    }

    /***
     * Setting the panel
     */
    private void setPanel() {
        //Used later for gaps
        experimentLayout = new GridLayout(0,2);

        //Layouts
        contentPane.setLayout(experimentLayout);
        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());

        //Adding a label and a ComboBox
        animalLabel = new JLabel("Choose an animal:");
        animalBox = new JComboBox<String>(ceatures);
        contentPane.add(animalLabel);
        contentPane.add(animalBox);
        add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(experimentLayout);

        //Adding save and cancel buttons
        b1=new JButton("Save State");
        b2=new JButton("Cancel");
        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.setVisible(true);

        //Contentpane on center of dialog & buttons on bottom
        DialogPanel.add(contentPane,BorderLayout.CENTER);
        DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
        //Adding listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
    }


    @Override
    /***
     * Dialog buttons functionality
     */
    public void actionPerformed(ActionEvent e) {
        //CLICK "Cancel"
        if (e.getSource() == b2)
            dispose();
        //CLICK "Save State"
        else if(e.getSource() ==b1){
            saveState();
        }
    }

    private void saveState(){
        String animal= animalBox.getItemAt(animalBox.getSelectedIndex());

        if(animal!= null) {
            int id_of_animal = animal.charAt(animal.length() - 1) - 48;
            animal = animal.substring(0, animal.length() - 2);

            for (Swimmable temp : ap.getSwimSet()) {
                if (temp.getAnimalID() == id_of_animal) {

                    if (Objects.equals(temp.getAnimalName(), animal) &&
                            Objects.equals(temp.getAnimalName(), "Fish"))
                    {
                        originator.setState(new MementoState((Fish) temp));
                        caretaker.addMemento(originator.save());
                    }
                    else if (Objects.equals(temp.getAnimalName(), animal) &&
                            Objects.equals(temp.getAnimalName(), "Jellyfish"))
                    {
                        originator.setState(new MementoState((Jellyfish) temp));
                        caretaker.addMemento(originator.save());
                    }
                }
            }

            for (Immobile temp : ap.getImmobileSet()) {
                if (temp.getPlantId() == id_of_animal) {
                    if (Objects.equals(temp.name, animal) && Objects.equals(temp.name, "Laminaria")) {
                        originator.setState(new MementoState((Laminaria) temp));
                        caretaker.addMemento(originator.save());
                    } else if (Objects.equals(temp.name, animal) && Objects.equals(temp.name, "Zostera")) {
                        originator.setState(new MementoState((Zostera) temp));
                        caretaker.addMemento(originator.save());
                    }
                }
            }
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null,"There are no animals to save");
        }
    }
}
