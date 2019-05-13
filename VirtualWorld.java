package VirtualWorldJava;

import javax.swing.JPanel;

import VirtualWorldJava.General.World;

public class VirtualWorld extends JPanel {

    private static final long serialVersionUID = 1L;

    VirtualWorld() {
        super();
    }

    public static void main(String[] args) {

        VirtualWorld vw = new VirtualWorld();

        World world = new World();

        while (world.GetStart() == false) {
            vw.repaint();
        }

        world.Init(2);

        while (world.GetFinish() == false) {
            world.NextTurn();
            vw.repaint();
        }

        world.Notify("You died!");

    }

}