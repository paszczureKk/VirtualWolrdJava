package VirtualWorldJava.General.Entities.Common.Animals;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.Navigation;
import VirtualWorldJava.General.Navigation.WorldDirections;
import VirtualWorldJava.General.Navigation.Point;
import VirtualWorldJava.General.Utilities.Utilities;

public class Antelope extends Animal<Antelope> {
    
    public Antelope() {
        super(4, 4, 0, null);
    }
    public Antelope(int a, World w) {
        super(4, 4, a, w);
    }
    @Override
    public char GetToken() {
        return 'A';
    }
    
    @Override
    public ImageIcon GetImage() {
        return ImageLoader.antelope;
    }

    public void Action() {
        for (int i = 0; i < 2; i++) {
            if (this.IsAlive() == true) {
                super.Action();
            }
        }
    }
    public boolean Collision(Organism o) {
        if (0.5f < Utilities.random(0.0f, 1.0f)) {
            Point newP = Navigation.Translate(location, WorldDirections.DIR_NULL);
    
            Point p = location;
            Move(newP);
    
            this.Move(p);
    
            return false;
        }
    
        return super.Collision(o);
    }
    @Override
    public String toString() {
        return "Antelope";
    }

};