package VirtualWorldJava.General.Entities.Common.Plants;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Entities.Abstract.Plant;

public class Dandelion extends Plant<Dandelion> {

    public Dandelion() {
        super(0, 0, null, 0.05f);
    }
    public Dandelion(int a, World w) {
        super(0, a, w, 0.05f);
    }

    @Override
    public ImageIcon GetImage() {
        return ImageLoader.dandelion;
    }
    @Override
    public char GetToken() {
        return 'D';
    }

    public void Action() {
        for (int i = 0; i < 3; i++) {
            super.Action();
        }
    }
    @Override
    public String toString() {
        return "Dandelion";
    }

};