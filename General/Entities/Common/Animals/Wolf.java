package VirtualWorldJava.General.Entities.Common.Animals;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Animal;

public class Wolf extends Animal<Wolf> {

    public Wolf() {
        super(9, 5, 0, null);
    }
    public Wolf(int a, World w) {
        super(9, 5, a, w);
    }
    
    @Override
    public ImageIcon GetImage() {
        return ImageLoader.wolf;
    }

    @Override
    public String toString() {
        return "Wolf";
    }

};