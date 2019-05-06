package VirtualWorldJava.General.Entities.Common.Plants;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Belladonna extends Plant<Belladonna> {
    
    public Belladonna() {
        super(99, 0, 'B', null, 0.03f);
    }
    public Belladonna(int a, World w) {
        super(99, a, 'B', w, 0.03f);
    }
    public boolean Collision(Organism o) {
        o.Kill(this.toString());

        super.Collision(o);
    
        return false;
    }
    @Override
    public String toString() {
        return "Belladonna";
    }
	
};