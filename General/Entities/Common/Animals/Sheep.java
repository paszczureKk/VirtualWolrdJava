package VirtualWorldJava.General.Entities.Common.Animals;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Animal;

public class Sheep extends Animal<Sheep> {

    public Sheep() {
        super(4, 4, 0, null);
    }
    public Sheep(int a, World w) {
        super(4, 4, a, w);
    }
    
    @Override
    public ImageIcon GetImage() {
        return ImageLoader.sheep;
    }
    @Override
    public char GetToken() {
        return 'S';
    }

    @Override
    public String toString() {
        return "Sheep";
    }

};