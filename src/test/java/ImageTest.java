import org.junit.jupiter.api.Test;
import tank.ImageUtil;
import tank.ResourceMgr;
import tank.Tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    public void testLoadImage() {
        try {
            BufferedImage image = ImageIO.read(new File("F:\\IdeaProjects\\tank_war\\src\\images\\bulletR.gif"));
            assertNotNull(image);
            BufferedImage image2 = ImageIO.read(Tank.class.getClassLoader()
                    .getResourceAsStream("images/bulletR.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRotateImage() {
        try {
            BufferedImage image3 = ImageIO.read(Tank.class.getClassLoader()
                    .getResourceAsStream("images/bulletR.gif"));
            image3 = rotateImage(image3, 90);
            assertNotNull(image3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage rotateImage(final BufferedImage bufferedimage,
                                      final int degree) {
        int h = bufferedimage.getHeight();
        int w = bufferedimage.getWidth();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w,h,type)).createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage,0,0,null);
        graphics2d.dispose();
        return img;
    }
}
