package strategy;

import tank.Bullet;
import tank.Player;
import tank.Tank;
import tank.TankFrame;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Player p) {
        TankFrame.INSTANCE.add(new Bullet(p.getX(), p.getY(), p.getDir(), p.getGroup()));
    }
}
