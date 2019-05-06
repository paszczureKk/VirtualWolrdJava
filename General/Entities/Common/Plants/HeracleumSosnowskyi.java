package VirtualWorldJava.General.Entities.Common.Plants;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class HeracleumSosnowskyi extends Plant<HeracleumSosnowskyi> {
    
    public HeracleumSosnowskyi() {
        super(10, 0, 'H', null, 0.01f);
    }
    public HeracleumSosnowskyi(int a, World w) {
        super(10, a, 'H', w, 0.01f);
    }
    public void Action() {
        this.world.RemoveFromWorld(this.toString(), this.location, 
        (Organism o) -> {
            if (o.IsAnimal() == false) {
                return false;
                }
            if (o.toString() == "CyberSheep") {
                return false;
            }
            return true;
            });

        super.Action();
    }
    public boolean Collision(Organism o) {
        this.Kill(o.toString());

        if (o.toString() != "CyberSheep") {
            o.Kill(this.toString());
        }
    
        return false;
    }
    @Override
    public String toString() {
        return "HeracleumSosnowskyi";
    }
        
};