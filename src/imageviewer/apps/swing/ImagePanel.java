package imageviewer.apps.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage bitmap;
    
    public ImagePanel() {
        try {
            bitmap = ImageIO.read(new File("fotos/foto1.jpg"));
        } catch (IOException ex) {
        }
    }
    
    @Override
    public void paint(Graphics g){
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        double ir = (double) iw / ih;
        
        int pw = getWidth();
        int ph = getHeight();
        double pr = (double) pw / ph;
        
        int sw = ir > pr ? pw : (int) (iw * (double) ph / ih);
        int sh = ir > pr ? (int) (ih * (double) pw / iw) : ph;
        int sx = (pw - sw) / 2;
        int sy = (ph - sh) / 2;
        
        g.drawImage(bitmap, sx, sy, sw, sh, null);
        
    }
}

/*
iw: Image Width
ih: Image Height
ir: Image Ratio = Image Width / Image Height
pw: Panel Width
ph: Panel Heigth
pr: Panel Ratio = Panel Width / Panel Heigth

800 x 600 (1.3333) -> 400 x 300 (1.3333) -> 400x300
800 x 600 (1.3333) -> 1600 x 1200(1.3333) -> 1600x1200
800 x 600 (1.3333) -> 400 x 600 (0.6666) -> 400x600 = 400 / 800
800 x 600 (1.3333) -> 800 x 300 (2.6666) -> 800x300 / 600 x 300

ir > pr -> pw x ih = pw / iw
ir < pr -> iw x ph / ih x ph

image (800x600) -> panel (400x55) -> scale(800 x 55/ 600 x 55)
*/
