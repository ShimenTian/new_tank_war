package tank;

import strategy.DefaultFireStrategy;
import strategy.FireStrategy;
import strategy.FourDirFireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private static final int SPEED = 5;
    ClassLoader loader = Player.class.getClassLoader();
    private int x, y;
    private Direction dir;
    private boolean bL, bR, bU, bD;
    private  boolean live  = true;
    private boolean moving = false;
    private Group group;

    public Player(int x, int y, Direction dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.initFireStrategy();
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
//        try {
//            BufferedImage image2 = ImageIO.read(Tank.class.getClassLoader()
//                    .getResourceAsStream("images/GoodTank1.png"));
//            g.drawImage(image2,x,y,null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if(!this.isLive()) {
            return;
        }
        if (group == Group.GOOD) {
            switch (dir) {
//            case L -> g.drawImage(ResourceMgr.goodTankL,x,y,null);
//            case R -> g.drawImage(ResourceMgr.goodTankR,x,y,null);
//            case U -> g.drawImage(ResourceMgr.goodTankU,x,y,null);
//            case D -> g.drawImage(ResourceMgr.goodTankD,x,y,null);
                case L:
                    g.drawImage(ResourceMgr.goodTankL, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.goodTankU, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD, x, y, null);
                    break;
            }
        }
        if (group == Group.BAD) {
            switch (dir) {
//            case L -> g.drawImage(ResourceMgr.goodTankL,x,y,null);
//            case R -> g.drawImage(ResourceMgr.goodTankR,x,y,null);
//            case U -> g.drawImage(ResourceMgr.goodTankU,x,y,null);
//            case D -> g.drawImage(ResourceMgr.goodTankD,x,y,null);
                case L:
                    g.drawImage(ResourceMgr.badTankL, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.badTankR, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.badTankU, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.badTankD, x, y, null);
                    break;
            }
        }
        move();
    }

    public void pressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setMainDir();
    }

    public void released(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setMainDir();
    }
   private FireStrategy strategy = null;

    private void initFireStrategy() {
        String className = PropertyMgr.get("tankFireStrategy");
        try {
            Class clazz = loader.loadClass("strategy." + className);
            strategy = (FireStrategy) (clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fire() {
        initFireStrategy();
        strategy.fire(this);
    }

    private void setMainDir() {
        if (!bL && !bU && !bR && !bD) {
            moving = false;
        } else {
            moving = true;
            if (bL && !bU && !bR && !bD) {
                dir = Direction.L;
            }
            if (!bL && bU && !bR && !bD) {
                dir = Direction.U;
            }
            if (!bL && !bU && bR && !bD) {
                dir = Direction.R;
            }
            if (!bL && !bU && !bR && bD) {
                dir = Direction.D;
            }
        }
    }

    private void move() {
        if (this.moving == true) switch (dir) {
            case D -> y += SPEED;
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
        }
    }

    public void die() {
        this.setLive(false);
    }
}
