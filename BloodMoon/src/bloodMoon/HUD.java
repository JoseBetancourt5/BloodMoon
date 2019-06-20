package bloodMoon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class HUD {

    BufferedImage itemBar;
    BufferedImage sword;
    BufferedImage wand;
    BufferedImage healthPotion;
    BufferedImage shield;
    BufferedImage lightning;
    BufferedImage bomb;
    Font font;
    Player player;


    
    public HUD (Player p) {
    	
    	player  =  p;
        font = new Font("InfoErrataMsg", Font.BOLD, 20);

    	
        try {
            itemBar = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Item Bar.png"));
			sword = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Sword Angled.png"));
			wand = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Wand Angled.png"));
			healthPotion = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Health Potion.png"));
			shield = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Shield.png"));
			lightning = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Lightning.png"));
			bomb = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\bomb.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        

        g2.setFont(font);
        g2.setColor(Color.green);

        

        g2.drawImage(itemBar, 543, 650, 284, 56, null);
        g2.drawImage(sword, 558, 666, 30, 30, null);
        g2.drawImage(wand, 606, 666, 30, 30, null);
        
        g2.drawImage(healthPotion, 649, 665, 30, 30, null);
        g2.drawString(player.healthPots + "", 668, 696);
        
        g2.drawImage(shield, 693, 665, 30, 30, null);
        g2.drawString(player.barriers + "", 713, 696);
        
        g2.drawImage(lightning, 742, 668, 26, 26, null);
        g2.drawString(player.bolts + "", 758, 696);
        
        g2.drawImage(bomb, 789, 666, 26, 26, null);
        g2.drawString(player.bombs + "", 804, 696);

        

       
  }
}

