package VirtualWorldJava.General.Abstract;

import VirtualWorldJava.General.World;
import VirtualWorldJava.General.Navigation.*;

public abstract class Organism {
    
    //#region CONSTUCTOR

    public Organism(int s, int i, int a, char ch, World w) {

        strength = s;
        initiative = i;
        age = a;
        location = Navigation.NULL_POINT;
        image = ch;
        world = w;
        alive = true;

    }

    //#endregion

    //#region PRIVATE_VARIABLES

    private int strength;
    private int initiative;
    private int age;
    private Point location;
    private char image;
    private World world;
    private boolean alive;

    //#endregion

    //#region PRIVATE_FUNCTIONS

    //#endregion

    //#region PUBLIC_FUNCTIONS

    //#endregion

}