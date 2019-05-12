package VirtualWorldJava.General.Entities.Common.Plants;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Guarana extends Plant<Guarana> {

    public Guarana() {
        super(0, 0, null, 0.07f);
    }
    public Guarana(int a, World w) {
        super(0, a, w, 0.07f);
    }
    
    @Override
    public ImageIcon GetImage() {
        return ImageLoader.guarana;
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