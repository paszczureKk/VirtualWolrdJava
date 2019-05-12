package VirtualWorldJava.General.Entities.Unique;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Engine.InputEnum;
import VirtualWorldJava.General.Engine.InputHandler;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.Navigation;
import VirtualWorldJava.General.Navigation.WorldDirections;

public class Human extends Animal<Human> {

    public Human(int a, World w) {
        super(5, 4, a, w);

        this.cooldown = 0;
        this.active = 0;

        this.inputHandler = new InputHandler(this);
    }

    @Override
    public ImageIcon GetImage() {
        return ImageLoader.player;
    }

    public void Action() {

        world.LegendUpdate(WorldDirections.DIR_NULL, this.message);

        this.CoolDown();

        world.Draw();

        this.MakeMove();

        this.ActiveDown();

        world.LegendUpdate(WorldDirections.DIR_NULL, this.message);
        world.ClearOutput();
    }

    public boolean Collision(Organism o) {
        if (active > 0) {
            o.Move(Navigation.Translate(this.location, WorldDirections.DIR_NULL));

            world.Draw();
            // world.LegendUpdate();

            return false;
        }

        return true;
    }

    public void Kill(String s) {

    }

    @Override
    public String toString() {
        return "Player";
    }

    public int GetCooldown() {
        return this.cooldown;
    }

    public int GetDuration() {
        return this.active;
    }

    public void SetInput(InputEnum ie) {
        this.control = ie;
    }

    private int cooldown;
    private int active;
    private InputHandler inputHandler;
    private InputEnum control;
    private String message;

    void CoolDown() {
        if (this.cooldown > 0) {
            this.cooldown--;
        }
    }

    void ActiveDown() {
        if (this.active > 0) {
            this.active--;
            if (this.active == 0) {
                this.cooldown = 5;
                this.message = "";
            }
        }
    }

    void Special() {
        if (this.cooldown > 0 || this.active > 0) {
            return;
        }

        this.active = 5;
        this.message = "Player empowered.";
    }

    void MakeMove() {
        this.control = null;

        do {

            System.out.println("Making move..");

            /*try {
                System.out.println("Handler control..");
                inputHandler.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                assert true;
            }*/

            if (this.control == InputEnum.SPACE) {
                Special();
            }

            this.world.LegendUpdate(ControlParse(this.control), this.message);
        } while (this.control != InputEnum.ENTER);

        Move(Navigation.Translate(this.location, ControlParse(this.control)));
    }

    WorldDirections ControlParse(InputEnum ie) {

        if(ie == null) {
            return WorldDirections.DIR_NULL;
        }

        switch (ie) {
            case LEFT:
                return WorldDirections.WEST;
            case RIGHT:
                return WorldDirections.EAST;
            case UP:
                return WorldDirections.NORTH;
            case DOWN:
                return WorldDirections.SOUTH;
            default:
                return WorldDirections.DIR_NULL;
        }
    }

};