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
        Scale scale = new Scale(bitmap.getWidth(), bitmap.getHeight(), getWidth(), getHeight());
        g.drawImage(bitmap, scale.x(), scale.y(), scale.width(), scale.heigth(), null);
    }

    private static class Scale {
        private final int iw;
        private final int ih;
        private final int pw;
        private final int ph;

        private Scale(int iw, int ih, int pw, int ph) {
            this.iw = iw;
            this.ih = ih;
            this.pw = pw;
            this.ph = ph;
        }
        
        int x(){
            return (pw - width()) / 2;
        }

        int y(){
            return (ph - heigth()) / 2; 
        }
        
        int width(){
           return adjustWidth() ? pw : (int) (iw * (double) ph / ih);
        }
        
        int heigth(){
            return adjustWidth() ? (int) (ih * (double) pw / iw) : ph;
        }
                
        private boolean adjustWidth(){
            return iw * ph > pw * ih;
        }
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
