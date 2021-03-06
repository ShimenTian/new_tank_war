package tank;

import java.awt.*;

public class Explode {
    private int x, y;
    private  int width, height;
    private int step;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step ++;
        if(step > 15) {
            this.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
