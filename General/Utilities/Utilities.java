package VirtualWorldJava.General.Utilities;

import java.util.Random;

public class Utilities {

    private static Random r = new Random();

    public static int random(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
    public static double random(double min, double max) {
        return min + r.nextDouble() * (max - min);
    }

}