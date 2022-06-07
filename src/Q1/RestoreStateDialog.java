package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RestoreStateDialog extends JDialog implements ActionListener {
    private AquaPanel ap;                               //AquaPanel object
    private String[] states ;                           //List of strings
    private JLabel animalLabel;                         //Label
    private JComboBox<String> animalBox;                //ComboBox to choose animals
    private GridLayout experimentLayout;                //GridLayout for gaps
    private JPanel DialogPanel,buttonsPanel;            //Panels for the dialog
    private JButton b1,b2;                              //Buttons for dialog
    private Caretaker caretaker;                        //Manages the memento state
    private Originator originator ;                     //Saves the states into caretaker
    private final JPanel contentPane = new JPanel();    //contentPane for dialog

    /***
     *
     * @param ap - Main panel passed by reference
     * @param caretaker - Set the caretaker
     * @param originator - Set the originator
     */
    public RestoreStateDialog(AquaPanel ap,Caretaker caretaker,Originator originator){
        super();

        //Starting the dialog
        this.ap = ap;
        this.caretaker = caretaker;
        this.originator = originator;

        //Create states list
        states = new String[caretaker.getsize()];
        for (int i =0 ; i <caretaker.getsize();i++){
            for(Swimmable j :ap.getSwimSet()){
                if(caretaker.getMemento(i).getState().id == j.getAnimalID() )
                    states[i] = caretaker.getMemento(i).getState().toString();
            }
        }
        //Basic dialog settings
        setSize(450, 105);
        setLayout(new BorderLayout());
        this.setTitle("Restore State Dialog");
        this.setLocationRelativeTo(null);

        setPanel();
        //Add the panel to the JDialog dialogTable
        add(DialogPanel);
        //Show the dialog
        setVisible(true);
    }

    /***
     * Creating a panel and adding relevant parameters
     */
    private void setPanel()
    {
        //Layouts
        experimentLayout = new GridLayout(0,2);
        contentPane.setLayout(experimentLayout);

        //Creating panels
        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());

        //Creating a label and a box
        animalLabel = new JLabel("Choose state:");
        animalBox = new JComboBox<String>(states);
        contentPane.add(animalLabel);
        contentPane.add(animalBox);

        //Add contentPane to the center
        add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(experimentLayout);

        //Creating buttons
        b1=new JButton("Restore State");
        b2=new JButton("Cancel");
        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.setVisible(true);

        //Adding buttons and contentpane
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
        //CLICK "Restore State"
        else if(e.getSource() ==b1){
            RestoreState();
        }
    }

    public void RestoreState(){
        String animal= animalBox.getItemAt(animalBox.getSelectedIndex());
        if (animal != null) {
            int id_of_animal = animal.charAt(animal.length() - 10) - 48;
            String date = animal.substring(animal.length() - 8);
            animal = animal.substring(0, animal.length() - 10);
            for (Swimmable temp : ap.getSwimSet()) {
                if (temp.getAnimalID() == id_of_animal && Objects.equals(animal, temp.getAnimalName())) {
                    temp.SetMementoState(caretaker.getMemento(date).getState());
                    dispose();
                }
            }
            for (Immobile plant : ap.getImmobileSet()) {
                if (plant.getPlantId() == id_of_animal && Objects.equals(animal, plant.name)) {
                    plant.SetMementoState(caretaker.getMemento(date).getState());
                    dispose();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"there is no sates to restore");
        }
    }
}
