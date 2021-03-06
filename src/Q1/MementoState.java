package Q1;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//Memento state that contain the info of sea creature in the save moment
public class MementoState {
    //properties of the sea creature
    protected String kind_of_animal;
    protected Color color;
    protected int size,x,y,hor_speed,ver_speed,id;
    protected LocalDateTime myDateObj ; // time of the save

    /***
     * Constructor
     * Building state according to a fish
     * @param fish
     */
    public MementoState(Fish fish){
        this.kind_of_animal = "Fish";
        this.color = fish.getCol();
        this.size = fish.getSize();
        this.x = fish.getX_front();
        this.y = fish.getY_front();
        this.hor_speed = fish.getHorSpeed();
        this.ver_speed = fish.getVerSpeed();
        this.id = fish.getAnimalID();
        myDateObj = LocalDateTime.now();
    }

    /***
     * Constructor
     * Building state according to a jellyfish
     * @param jellyfish
     */
    public MementoState(Jellyfish jellyfish){
        this.kind_of_animal = "Jellyfish";
        this.color = jellyfish.getCol();
        this.size = jellyfish.getSize();
        this.x = jellyfish.getX_front();
        this.y = jellyfish.getY_front();
        this.hor_speed = jellyfish.getHorSpeed();
        this.ver_speed = jellyfish.getVerSpeed();
        this.id = jellyfish.getAnimalID();
        myDateObj = LocalDateTime.now();
    }

    /***
     * Constructor
     * Building state according to a zostera
     * @param zostera
     */
    public MementoState(Zostera zostera){
        this.kind_of_animal = "Zostera";
        this.color = Color.green;
        this.x = zostera.x;
        this.y = zostera.y;
        this.size = zostera.size;
        this.id = zostera.getPlantId();
        myDateObj = LocalDateTime.now();
    }
    /***
     * Constructor
     * Building state according to a laminaria
     * @param laminaria
     */
    public MementoState(Laminaria laminaria){
        this.kind_of_animal = "Laminaria";
        this.color = Color.green;
        this.x =laminaria.x;
        this.y = laminaria.y;
        this.size = laminaria.size;
        this.id = laminaria.getPlantId();
        myDateObj = LocalDateTime.now();
    }

    /***
     * @return Sting of state in format animal name + id + time "hh:mm:ss"
     */
    public String toString(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return this.kind_of_animal+ this.id +" "+formattedDate ;
    }

    /***
     * @return The time this state created in format "hh:mm:ss"
     */
    public String getTimeSaved(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        return  myDateObj.format(myFormatObj);
    }

}
