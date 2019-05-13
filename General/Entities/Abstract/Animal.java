package VirtualWorldJava.General.Entities.Abstract;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Navigation.*;

public abstract class Animal<T extends Organism> extends Organism {

    public Animal(int s, int i, int a, World w) {
        super(s, i, a, w);
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

            try {
                Organism org = this.getClass().getConstructor(int.class, World.class).newInstance(this.world.GetAge(), this.world);

                world.AddToWorld(org, p);
    
                world.Notify(this.toString() + " has been born on " + p.toString());
    
                return false;
            } catch (Exception e) {
                return false;
            }

        }

        return true;
    }

    @Override
    public boolean IsAnimal() {
        return true;
    }

    @Override
    public void Move(Point p) {

        Organism o = world.GetAt(p);

        if (o == null) {
            world.MoveTo(p, this);
        }
        else {
            if (o.Collision(this) == true) {
                if (this.Collision(o) == true) {
                    Fight(o);
                }
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