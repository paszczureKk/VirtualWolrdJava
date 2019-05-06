package VirtualWorldJava.General.Entities.Common.Plants;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Dandelion extends Plant<Dandelion> {

    public Dandelion(int a, World w) {
        super(0, a, 'D', w, 0.05f);
    }
    public void Action() {
        for (int i = 0; i < 3; i++) {
            super.Action();
        }
    }
    @Override
    public String toString() {
        return "Dandelion";
    }

};