package tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Bullet {
    public static final int SPEED = 10;
    private int x, y;
    private Direction dir;
    private Group group;
    private boolean live = true;

    public Bullet(int x, int y, Direction dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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
        if (group == Group.GOOD) {
            switch (dir) {
//            case L -> g.drawImage(ResourceMgr.goodTankL,x,y,null);
//            case R -> g.drawImage(ResourceMgr.goodTankR,x,y,null);
//            case U -> g.drawImage(ResourceMgr.goodTankU,x,y,null);
//            case D -> g.drawImage(ResourceMgr.goodTankD,x,y,null);
                case L:
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
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
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
                    break;
            }
        }
        move();
    }

    private void move() {
        switch (dir) {
            case D -> y += SPEED;
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
        }
        boundsCheck();
    }

    public void collideWithTank(Tank tank) {
        if(!tank.isLive()) {
            return;
        }
        Rectangle rect = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
        if(rect.intersects(rectTank)) {
            TankFrame.INSTANCE.add(new Explode(x, y));
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.setLive(false);
    }

    private void boundsCheck() {
        if(x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }
}
