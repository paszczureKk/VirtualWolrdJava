package VirtualWorldJava.General.Entities.Abstract;

import java.util.Comparator;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Navigation.*;

public abstract class Organism implements Comparator<Organism> {
    
    //#region CONSTUCTOR

    public Organism(int s, int i, int a, char ch, World w) {

        this.strength = s;
        this.initiative = i;
        this.age = a;
        this.location = Navigation.NULL_POINT;
        this.image = ch;
        this.world = w;
        this.alive = true;

    }

    //#endregion

    //#region PROTECTED_VARIABLES

    protected int strength;
    protected int initiative;
    protected int age;
    protected Point location;
    protected char image;
    protected World world;
    protected boolean alive;

    //#endregion

    //#region PROTECTED_FUNCTIONS

    protected void Fight(Organism o) {
        if (this.GetStrength() < o.GetStrength() == false) {
            Point p = o.GetLocation();
            o.Kill(this.toString());
            this.Move(p);
        }
        else {
            this.Kill(o.toString());
        }
    }

    //#endregion

    //#region PUBLIC_FUNCTIONS

    public Point GetLocation() {
        return this.location;
    }
	public void SetLocation(Point p) {
        this.location = p;
    }
	public int GetStrength() {
        return this.strength;
    }
	public void SetStrength(int value) {
        this.strength = value;
    }
    public int GetInitiative() {
        return this.initiative;
    }
    public void SetInitiative(int value) {
        this.initiative = value;
    }
	public int GetAge() {
        return this.age;
    }
    public void SetAge(int value) {
        this.age = value;
    }
	public char GetImage() {
        return this.image;
    }
    public void SetWorldRef(World w) {
        this.world = w;
    }

    public abstract boolean IsAnimal();
    public boolean IsAlive() {
        return this.alive;
    }
    
    public abstract void Action();
	public abstract boolean Collision(Organism o);
	public void Draw() {
        System.out.println(this.image);
    }
    public abstract void Move(Point p);
    
    public void Kill(String s) {
        alive = false;
	    world.Notify(s);
	    world.RemoveFromWorld(this);
    }
	public void Buff(int value) {
        this.strength += value;
    }

    
    @Override
    public int compare(Organism current, Organism other) {
        if (current.GetInitiative() != other.GetInitiative()) {
            if(current.GetInitiative() > other.GetInitiative()) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            if(current.GetAge() > other.GetAge()) {
                return 1;
            }
            else {
                if(current.GetAge() < other.GetAge()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    //#endregion

}