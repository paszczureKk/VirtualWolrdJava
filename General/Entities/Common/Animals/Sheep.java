package VirtualWorldJava.General.Entities.Common.Animals;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Animal;

public class Sheep extends Animal<Sheep> {
    Sheep(int a, World w) {
        super(4, 4, a, 'S', w);
    }
    @Override
    public String toString() {
        return "Sheep";
    }
};