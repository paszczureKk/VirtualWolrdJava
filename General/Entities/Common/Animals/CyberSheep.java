package VirtualWorldJava.General.Entities.Common.Animals;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;

public class CyberSheep extends Animal<CyberSheep> {

    public CyberSheep(int a, World w) {
        super(11, 4, a, 'C', w);
    }
    public void Action() {
        super.Action();
    }
    public boolean Collision(Organism o) {
        if (o.toString() == "HeracleumSosnowskyi") {
            o.Kill(this.toString());
            return false;
        }
    
        return super.Collision(o);
    }
    @Override
    public String toString() {
        return "CyberSheep";
    }

};