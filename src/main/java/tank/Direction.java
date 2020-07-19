package tank;

import java.util.Random;

public enum Direction {
    L,U,R,D;

    private static Random r = new Random();

    public static Direction randomDir() {
        return values()[r.nextInt(Direction.values().length)];
    }
}
