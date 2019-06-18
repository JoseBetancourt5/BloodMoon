package bloodMoon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Bullet {

	BufferedImage playerFire;
	BufferedImage enemyFire;
	
	Player player;
	public double bulletX;
	public double bulletY;
	public int width;
	public int height;
	public double velX;
	public double velY;
	public boolean isForPlayer;
	
	public Bullet (int x, int y, double vX, double vY, int w, int h, boolean iFP, Player p) {
		
		player = p;
		bulletX = x;
		bulletY = y;
		velX = vX;
		velY = vY;
		width = w;
		height = h;
		isForPlayer = iFP;
		
		try {
			playerFire = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\blueFire.png"));
			enemyFire = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\redFire.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		if (isForPlayer) {
			g2.drawImage(playerFire, (int)bulletX, (int)bulletY, width, height, null);
		}
		else {
			g2.drawImage(enemyFire, (int)bulletX, (int)bulletY, width, height, null);
		}
	}
	
	public void physics () {
		bulletX += velX;
		bulletY += velY;
	}
	

	public void checkBoundaries () {
		if (bulletX >1400 || bulletX<-20 || bulletY<-20 || bulletY>700) {
			player.bullets.remove(this);
		}
	}
	
	public Rectangle hitbox ( ) {
		return (new Rectangle ((int)bulletX, (int)bulletY, width, height));
	}
		
}

