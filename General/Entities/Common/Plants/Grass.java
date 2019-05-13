package VirtualWorldJava.General.Entities.Common.Plants;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Grass extends Plant<Grass> {

    public Grass() {
        super(0, 0, null, 0.07f);
    }
    public Grass(int a, World w) {
        super(0, a, w, 0.07f);
    }

    @Override
    public ImageIcon GetImage() {
        return ImageLoader.grass;
    }
    @Override
    public char GetToken() {
        return 'G';
    }

    @Override
    public String toString() {
        return "Grass";
    }
    
};