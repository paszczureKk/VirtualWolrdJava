package VirtualWorldJava.General.Entities.Abstract;

import VirtualWorldJava.General.Entities.Common.Animals.*;
import VirtualWorldJava.General.Entities.Common.Plants.*;
import VirtualWorldJava.General.Entities.Unique.Human;

public enum Entities {
    Antelope,
    CyberSheep,
    Fox,
    Sheep,
    Turtle,
    Wolf,
    Belladonna,
    Dandelion,
    Grass,
    Guarana,
    HeracleumSosnowskyi;

    public Organism Create() {
        switch (this) {
            case Antelope:
                return new Antelope();
            case CyberSheep:
                return new CyberSheep();
            case Fox:
                return new Fox();
            case Sheep:
                return new Sheep();
            case Turtle:
                return new Turtle();
            case Wolf:
                return new Wolf();
            case Belladonna:
                return new Belladonna();
            case Dandelion:
                return new Dandelion();
            case Grass:
                return new Grass();
            case Guarana:
                return new Guarana();
            case HeracleumSosnowskyi:
                return new HeracleumSosnowskyi();
            default:
                return null;
        }
    }
    public static Organism Create(char t) {
        switch (t) {
            case 'A':
                return new Antelope();
            case 'C':
                return new CyberSheep();
            case 'F':
                return new Fox();
            case 'S':
                return new Sheep();
            case 'T':
                return new Turtle();
            case 'W':
                return new Wolf();

            case 'B':
                return new Belladonna();
            case 'D':
                return new Dandelion();
            case 'G':
                return new Grass();
            case 'U':
                return new Guarana();
            case 'H':
                return new HeracleumSosnowskyi();

            case 'P':
                return new Human();

            default:
                return null;
        }
    }
}