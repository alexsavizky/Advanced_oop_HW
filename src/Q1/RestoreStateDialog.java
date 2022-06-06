package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestoreStateDialog extends JDialog implements ActionListener {
    private AquaPanel ap;
    private String[] states ;
    private JLabel animalLabel;
    private JComboBox<String> animalBox;
    private GridLayout experimentLayout;
    private AquaPanel panel;
    private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
    private JButton b1,b2;
    private Caretaker caretaker;
    private Originator originator ;

    private final JPanel contentPane = new JPanel();
    public RestoreStateDialog(AquaPanel ap,Caretaker caretaker,Originator originator){
        super();
        //Starting the dialog
        this.ap = ap;
        this.caretaker =caretaker;
        this.originator = originator;
        states = new String[caretaker.getsize()];
        for (int i =0 ; i <caretaker.getsize();i++){
            states[i] = caretaker.getMemento(i).getState().toString();
        }
        System.out.println(states[0]);
        setSize(450, 305);
        setLayout(new BorderLayout());
        this.setTitle("Restore State Dialog");
        this.setLocationRelativeTo(null);


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
        animalLabel = new JLabel("Choose state:");
        animalBox = new JComboBox<String>(states);

        contentPane.add(animalLabel);
        contentPane.add(animalBox);
        add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(experimentLayout);
        b1=new JButton("Restore State");
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
            for(Swimmable temp : ap.getSwimSet()){
                if (temp.getAnimalID() == id_of_animal){
                    temp.SetMementoState(originator.getState());
                    dispose();
                }
            }
        }
    }
}
