package bloodMoon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {

    BufferedImage itemBar;
    BufferedImage healthbar;
    BufferedImage manabar;
    BufferedImage stonewall;

    
    public HUD () {
        try {
            healthbar = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Health.png"));
            itemBar = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Item Bar.png"));
            manabar = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\manabar.png"));
            stonewall = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Stone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        

        
        g2.setColor(new Color(187,187,187));
        g2.fillRect(90, 725, 500,55);
        g2.fillRect(90, 810, 500,55);
        g2.fillRect(710, 740, 670, 110);
        g2.drawImage(healthbar, -10, 600, 670, 330, null);
        g2.drawImage(itemBar, 700, 722, 700, 150, null);
        g2.drawImage(manabar, 0, 790, 603, 89, null);
        g2.setColor(new Color(255,0,0));
        
        g2.drawImage(stonewall, 0, 700, 375, 200, null);
        g2.drawImage(stonewall, 375, 700, 375, 200, null);
        g2.drawImage(stonewall, 750, 700, 375, 200, null);
        g2.drawImage(stonewall, 1125, 700, 375, 200, null);
        //g2.fillRect(50, 715, 4*player.health, 30);
  }
}

