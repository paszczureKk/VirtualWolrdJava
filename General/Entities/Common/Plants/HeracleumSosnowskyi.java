package VirtualWorldJava.General.Entities.Common.Plants;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class HeracleumSosnowskyi extends Plant<HeracleumSosnowskyi> {
    
    public HeracleumSosnowskyi() {
        super(10, 0, null, 0.01f);
    }
    public HeracleumSosnowskyi(int a, World w) {
        super(10, a, w, 0.01f);
    }

    @Override
    public ImageIcon GetImage() {
        return ImageLoader.heracleum;
    }
    @Override
    public char GetToken() {
        return 'H';
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