package VirtualWorldJava.General.Entities.Abstract;

import VirtualWorldJava.General.Utilities.Utilities;
import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Navigation.Point;

public abstract class Plant<T> extends Organism {

    public Plant(int s, int a, char ch, World w, float p) {
        super(s, 0, a, ch, w);
        probability = p;
    }

    @Override
    public void Action() {

        if (probability < Utilities.random(0.0f, 1.0f)) {
            return;
        }

        Point p = world.SeekForFree(location);

        if (p == null) {
            return;
        }

        Organism org = (Organism)(new T(world.GetAge(), world));
        world.AddToWorld(org, p);

        world.Notify(this.toString() + " has grown on " + p.toString());
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