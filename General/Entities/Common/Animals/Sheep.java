package VirtualWorldJava.General.Entities.Common.Animals;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Animal;

public class Sheep extends Animal<Sheep> {

    public Sheep() {
        super(4, 4, 0, 'S', null);
    }
    public Sheep(int a, World w) {
        super(4, 4, a, 'S', w);
    }
    @Override
    public String toString() {
        return "Sheep";
    }

};