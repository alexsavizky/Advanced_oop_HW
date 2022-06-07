package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SaveStateDialog extends JDialog implements ActionListener {
    private AquaPanel ap;
    private String[] ceatures;
    private JLabel animalLabel;
    private JComboBox<String> animalBox;
    private GridLayout experimentLayout;
    private AquaPanel panel;
    private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
    private JButton b1,b2;
    private Caretaker caretaker;
    private Originator originator ;
    private final JPanel contentPane = new JPanel();
    public SaveStateDialog(AquaPanel ap,Caretaker caretaker,Originator originator){
        super();
        //Starting the dialog
        this.ap = ap;
        this.caretaker =caretaker;
        this.originator = originator;
        setSize(450, 305);
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

        this.panel=panel;

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
        animalBox = new JComboBox<String>(ceatures);


        contentPane.add(animalLabel);
        contentPane.add(animalBox);
        add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(experimentLayout);
        b1=new JButton("Save State");
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
        if (e.getSource() == b2)
            dispose();
        else if(e.getSource() ==b1){
            String animal= animalBox.getItemAt(animalBox.getSelectedIndex());
            int id_of_animal = animal.charAt(animal.length()-1)-48;
            animal = animal.substring(0,animal.length()-2);
            for(Swimmable temp : ap.getSwimSet()){
                if (temp.getAnimalID() == id_of_animal){
                    if(Objects.equals(temp.getAnimalName(), animal) && Objects.equals(temp.getAnimalName(), "Fish") ) {
                        originator.setState(new MementoState((Fish)temp));
                        caretaker.addMemento(originator.save());
                    }
                    else if (Objects.equals(temp.getAnimalName(), animal) && Objects.equals(temp.getAnimalName(), "Jellyfish")){
                        originator.setState(new MementoState((Jellyfish)temp));
                        caretaker.addMemento(originator.save());
                    }
                }
            }
            for(Immobile temp : ap.getImmobileSet()){
                if (temp.getPlantId() == id_of_animal){
                    if(Objects.equals(temp.name, animal) && Objects.equals(temp.name, "Laminaria") ) {
                        originator.setState(new MementoState((Laminaria)temp));
                        caretaker.addMemento(originator.save());
                    }
                    else if (Objects.equals(temp.name, animal) && Objects.equals(temp.name, "Zostera")){
                        originator.setState(new MementoState((Zostera)temp));
                        caretaker.addMemento(originator.save());
                    }
                }
            }
            dispose();
        }
    }
}
