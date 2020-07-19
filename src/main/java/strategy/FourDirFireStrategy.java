package strategy;

import tank.Bullet;
import tank.Direction;
import tank.Player;
import tank.TankFrame;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p) {
        Direction[] dirs = Direction.values();

        for (Direction d : dirs) {
            TankFrame.INSTANCE.add(new Bullet(p.getX(), p.getY(), d, p.getGroup()));
        }
    }
}

