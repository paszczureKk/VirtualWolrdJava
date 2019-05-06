package VirtualWorldJava.General.Entities.Common.Animals;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Animal;

public class Wolf extends Animal<Wolf> {

    Wolf(int a, World w) {
        super(9, 5, a, 'W', w);
    }
    @Override
    public String toString() {
        return "Wolf";
    }

};