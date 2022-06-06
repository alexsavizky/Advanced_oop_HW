package Q1;

import java.awt.*;

public class MementoState {
    protected String kind_of_animal;
    protected Color color;
    protected int size,x,y,hor_speed,ver_speed,id;
    public MementoState(Fish fish){
        this.kind_of_animal = "Fish";
        this.color = fish.getCol();
        this.size = fish.getSize();
        this.x = fish.getX_front();
        this.y = fish.getY_front();
        this.hor_speed = fish.getHorSpeed();
        this.ver_speed = fish.getVerSpeed();
        this.id = fish.getAnimalID();
    }
    public MementoState(Jellyfish jellyfish){
        this.kind_of_animal = "JellyFish";
        this.color = jellyfish.getCol();
        this.size = jellyfish.getSize();
        this.x = jellyfish.getX_front();
        this.y = jellyfish.getY_front();
        this.hor_speed = jellyfish.getHorSpeed();
        this.ver_speed = jellyfish.getVerSpeed();
        this.id = jellyfish.getAnimalID();
    }
    public MementoState(Zostera zostera){
        this.kind_of_animal = "Zostera";
        this.color = Color.green;
        this.x = zostera.x;
        this.y = zostera.y;
        this.size = zostera.size;
        this.id = zostera.getPlantId();
    }
    public MementoState(Laminaria laminaria){
        this.kind_of_animal = "Laminaria";
        this.color = Color.green;
        this.x =laminaria.x;
        this.y = laminaria.y;
        this.size = laminaria.size;
        this.id = laminaria.getPlantId();
    }
    public String toString(){
        return this.kind_of_animal+ this.id;
    }
}
