package VirtualWorldJava.General.Utilities;

import VirtualWorldJava.General.Entities.Abstract.Organism;

public class Functional {
    public interface ToKill {
        boolean kill(Organism o);
    }
}