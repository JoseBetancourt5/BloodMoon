package bloodMoon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
 
	BufferedImage background;


	public Background () {
		try {
			background = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background, 0, 0, 1400, 700, null);

   }
}

