package VirtualWorldJava.General.Entities.Common.Plants;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Guarana extends Plant<Guarana> {

    public Guarana() {
        super(0, 0, 'U', null, 0.07f);
    }
    public Guarana(int a, World w) {
        super(0, a, 'U', w, 0.07f);
    }
    public boolean Collision(Organism o) {
        o.Buff(3);

	    super.Collision(o);

	    return false;
    }
    @Override
    public String toString() {
        return "Guarana";
    }
    
};