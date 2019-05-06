package VirtualWorldJava.General.Entities.Common.Animals;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Utilities.Utilities;

public class Turtle extends Animal<Turtle> {
    public Turtle(int a, World w) {
        super(2, 1, a, 'T', w);
    }
    public void Action() {
        if (0.25f < Utilities.random(0.0f, 1.0f)) {
            return;
        }
    
        super.Action();
    }
    public boolean Collision(Organism o) {
        if (TypeCheck(o) == false) {
            if (o.GetStrength() < 5) {
                return false;
            }
        }
    
        return super.Collision(o);
    }
    @Override
    public String toString() {
        return "Turtle";
    }
};