package VirtualWorldJava.General.Entities.Common.Plants;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Grass extends Plant<Grass> {

    public Grass() {
        super(0, 0, 'G', null, 0.07f);
    }
    public Grass(int a, World w) {
        super(0, a, 'G', w, 0.07f);
    }
    @Override
    public String toString() {
        return "Grass";
    }
    
};