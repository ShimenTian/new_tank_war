package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 500, GAME_HEIGHT = 500;
    Image offScreenImage = null;
    private Player myTank;
    private List<Explode> explodes;
    private List<Tank> enemyTanks;
    private List<Bullet> bullets;

    private TankFrame() {
        initGameObjects();
        this.setTitle("tank war");
        this.setLocation(600,200);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setVisible(true);
        this.addKeyListener(new TankKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        for (int i = 0; i < enemyTanks.size(); i++) {
            if(!enemyTanks.get(i).isLive()) {
                enemyTanks.remove(i);
            } else {
                enemyTanks.get(i).paint(g);
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                bullets.get(i).collideWithTank(enemyTanks.get(j));
            }
            if (!bullets.get(i).isLive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()) {
                explodes.remove(i);
            } else {
                explodes.get(i).paint(g);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void add(Explode explode) {
        this.explodes.add(explode);
    }
    private void initGameObjects() {
        myTank = new Player(50,50, Direction.R, Group.GOOD);
        enemyTanks = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemyTanks.add(new Tank(100, 200, Direction.D, Group.BAD));
        }
    }



    //    KeyListener接口实现需要重写全部方法，继承KeyAdapter类不需要
//    private class TankKeyListener implements KeyListener {
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//
//        }
//    }
    public class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.pressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.released(e);
        }
    }
}
