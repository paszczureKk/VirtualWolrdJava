package VirtualWorldJava.General.Entities.Abstract;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Navigation.*;

public abstract class Animal<T> extends Organism {

    public Animal(int s, int i, int a, char ch, World w) {
        super(s, i, a, ch, w);
    }

    @Override
    public void Action() {

        Point newP = Navigation.Translate(location, WorldDirections.DIR_NULL);

        if (world.PointValidate(newP) == false) {
            return;
        }

        Move(newP);
    }
    @Override
    public boolean Collision(Organism o) {

        if (TypeCheck(o)) {
            Point p = world.SeekForFree(this.location);

            if (p == Navigation.NULL_POINT) {
                return false;
            }

            Organism org = (Organism)(new T(world->GetAge(), world));
            world.AddToWorld(org, p);

            world.Notify(this.toString() + " has been born on " + p.toString());

            return false;
        }

        return true;
    }

    @Override
    public boolean IsAnimal() {
        return true;
    }

    @Override
    public void Move(Point p) {
        Organism o = world.MoveTo(p, this);

        if (o == null) {
            return;
        }

        if (o.Collision(this) == true) {
            if (this.Collision(o) == true) {
                Fight(o);
            }
        }
    }
    public void Kill(String s) {
        super.Kill(this.toString() + " has been slain by " + s);
    }

    protected boolean TypeCheck(Organism o) {

        if (o.toString() == this.toString())
            return true;

        return false;
    }
};