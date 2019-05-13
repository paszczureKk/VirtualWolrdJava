package VirtualWorldJava.General.Entities.Abstract;

import VirtualWorldJava.General.Utilities.Utilities;
import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Navigation.Navigation;
import VirtualWorldJava.General.Navigation.Point;

public abstract class Plant<T extends Organism> extends Organism {

    public Plant(int s, int a, World w, float p) {
        super(s, 0, a, w);
        probability = p;
    }

    @Override
    public void Action() {

        if (probability < Utilities.random(0.0f, 1.0f)) {
            return;
        }

        Point p = world.SeekForFree(location);

        if (p == Navigation.NULL_POINT) {
            return;
        }

            try {
                Organism org = this.getClass().getConstructor(int.class, World.class).newInstance(this.world.GetAge(), this.world);

                world.AddToWorld(org, p);
    
                world.Notify(this.toString() + " has grown on " + p.toString());
    
                return;
            } catch (Exception e) {
                return;
            }
    }
    @Override
    public boolean Collision(Organism o) {
        this.Kill(o.toString());

        o.Move(this.GetLocation());

        return false;
    }
    @Override
    public void Move(Point p) {}
    public void Kill(String s) {
        super.Kill(this.toString() + " has been eaten by " + s);
    }

    @Override
    public boolean IsAnimal() {
        return false;
    }
    
    private float probability;
};