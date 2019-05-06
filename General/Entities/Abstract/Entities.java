package VirtualWorldJava.General.Entities.Abstract;

import VirtualWorldJava.General.Entities.Common.Animals.*;
import VirtualWorldJava.General.Entities.Common.Plants.*;

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

}