package VirtualWorldJava.General.Entities.Unique;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Entities.Abstract.Animal;
import VirtualWorldJava.General.Entities.Abstract.Organism;
import VirtualWorldJava.General.Navigation.Navigation;
import VirtualWorldJava.General.Navigation.WorldDirections;

public class Human extends Animal<Human> {

    public Human(int a, World w) {
        super(5, 4, a, 'P', w);

        cooldown = 0;
        active = 0;
    }
    public void Action() {

        world.LegendUpdate();
    
        this.CoolDown();
    
        world.Draw();
    
        this.MakeMove();
        
        this.ActiveDown();
    
        world.LegendUpdate();
        world.ClearOutput();
    }
    public boolean Collision(Organism o) {
        if (active > 0) {
            o.Move(Navigation.Translate(this.location, WorldDirections.DIR_NULL));

            world.Draw();
            world.LegendUpdate();
        
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

    int cooldown;
    int active;

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
    
        this.active = 5;
    }

    void MakeMove() {
        WorldDirections dir = WorldDirections.DIR_NULL;
	   
        do {
            dir = world.GetInput(dir, this.location);

            if (dir == WorldDirections.DIR_NULL) {
                Special();
            }
        } while (dir == WorldDirections.DIR_NULL);

        Move(Navigation.Translate(this.location, dir));
    }

};