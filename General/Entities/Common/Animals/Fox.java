package VirtualWorldJava.General.Entities.Common.Animals;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.*;

public class Fox extends Animal<Fox> {

    public Fox() {
        super(3, 7, 0, null);
    }
    public Fox(int a, World w) {
        super(3, 7, a, w);
    }
    
    @Override
    public ImageIcon GetImage() {
        return ImageLoader.fox;
    }
    @Override
    public char GetToken() {
        return 'F';
    }

    public void Action() {
        Point newP = Navigation.Translate(location, WorldDirections.DIR_NULL);

        if (world.PointValidate(newP) == false) {
            return;
        }

        Organism o = world.GetAt(newP);

        if (o != null && this.GetStrength() < o.GetStrength()) {
            return;
        }

        Move(newP);
    }
    @Override
    public String toString() {
        return "Fox";
    }
    
};