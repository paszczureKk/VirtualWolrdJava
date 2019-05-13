package VirtualWorldJava.General.Entities.Unique;

import javax.swing.ImageIcon;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Engine.ImageLoader;
import VirtualWorldJava.General.Engine.InputEnum;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.Navigation;
import VirtualWorldJava.General.Navigation.WorldDirections;

public class Human extends Animal<Human> {

    public Human() {
        super(5, 4, 0, null);

        this.cooldown = 0;
        this.active = 0;

        this.open = false;
    }
    public Human(int a, World w) {
        super(5, 4, a, w);

        this.cooldown = 0;
        this.active = 0;

        this.open = false;
    }

    @Override
    public ImageIcon GetImage() {
        return ImageLoader.player;
    }
    @Override
    public char GetToken() {
        return 'P';
    }

    public void Action() {

        world.LegendUpdate(WorldDirections.DIR_NULL);

        this.CoolDown();

        this.MakeMove();

        this.ActiveDown();

        world.LegendUpdate(WorldDirections.DIR_NULL);
    }

    public boolean Collision(Organism o) {
        if (active > 0) {
            o.Move(Navigation.Translate(this.location, WorldDirections.DIR_NULL));

            return false;
        }

        return true;
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
        if(open == true) {
            this.control = ie;
            this.changed = true;
        }
    }
    
    public void SetActive(int cd, int dur) {
        this.active = dur;
        this.cooldown = cd;
    }

    private int cooldown;
    private int active;
    private volatile InputEnum control;
    private boolean changed;
    private boolean open;

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
            }
        }
    }

    void Special() {
        if (this.cooldown > 0 || this.active > 0) {
            return;
        }

        
        System.out.println("SPECIOL");
        this.active = 5;
    }

    void MakeMove() {
        this.control = null;
        this.changed = false;

        this.open = true;

        InputEnum dir = null;

        do {

            if (this.control == InputEnum.SPACE && this.changed == true) {
                Special();
                this.world.LegendUpdate(ControlParse(dir));
                this.changed = false;
            } else {
                if (this.control == InputEnum.ENTER) {
                }
                else {
                    if(this.changed == true) {
                        this.world.LegendUpdate(ControlParse(dir));
                        dir = this.control;
                        this.changed = false;
                    }
                }
            }

            
        } while (this.control != InputEnum.ENTER);

        this.open = false;

        Move(Navigation.Translate(this.location, ControlParse(dir)));
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