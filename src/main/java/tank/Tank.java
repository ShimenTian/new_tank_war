package tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static tank.Direction.randomDir;

public class Tank {
    private static final int SPEED = 5;
    private int x, y, oldX, oldY;
    private Direction dir;
    private  boolean live  = true;
    private boolean moving = true;
    private Group group;

    public Tank(int x, int y, Direction dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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
        boundsCheck();
        move();
    }

    private void fire() {
        TankFrame.INSTANCE.add(new Bullet(x, y, dir, group));
    }

    private void move() {
        oldX = x;
        oldY = y;
        if (this.moving == true) switch (dir) {
            case D -> y += SPEED;
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
        }
        randomDir();
    }

    public void die() {
        this.setLive(false);
    }

    private void randomDir() {
        Random r = new Random();
        if(r.nextInt(100) > 90) {
            this.dir = Direction.randomDir();
        }
    }

    private void boundsCheck() {
        if(x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT - ResourceMgr.goodTankU.getHeight()) {
            x = oldX;
            y = oldY;
        }
    }
}
