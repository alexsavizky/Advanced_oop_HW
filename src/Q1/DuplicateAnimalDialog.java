package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class DuplicateAnimalDialog extends JDialog implements ActionListener {
    private AquaPanel ap;
    private String typeAnimal=null,colorType=null;
    private Color clr = null;
    private String[] colors = {"blue","red","green","black","yellow","pink"};
    private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types

    private AquaPanel panel;
    private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
    private Iterator<Swimmable> itrAnimals;
    private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
    private JButton b1,b2;
    private Swimmable duplicateSwimmable;
    public DuplicateAnimalDialog(AquaPanel ap)
    {
        super();
        //Starting the dialog
        this.ap = ap;
        setSize(450, 305);
        setLayout(new BorderLayout());
        this.setTitle("Duplicate Animal Dialog");
        this.setLocationRelativeTo(null);
        
        this.panel=panel;
        this.animalsSet= ap.getSwimSet();
        this.typeAnimal=null;

        setPanel();

        add(DialogPanel); // add the panel to the JDialog dialogTable 
        setVisible(true);// show the dialog
    }

    private void setPanel() {

        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());


        b1=new JButton("Duplicate");
        b2=new JButton("Cancel");

        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.setVisible(true);

        DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
        b1.addActionListener(this);
        b2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b2)
            dispose();
        else if(e.getSource() ==b1){
            Iterator<Swimmable> animal_iterator = this.animalsSet.iterator();
            while(animal_iterator.hasNext()){
                Swimmable temp =animal_iterator.next();
                if(Objects.equals(temp.getAnimalName(), "Fish")){
                    ap.addAnimal(temp.clone());

                }
            }
        }
    }
}
