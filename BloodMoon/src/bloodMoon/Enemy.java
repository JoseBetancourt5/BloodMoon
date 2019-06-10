package bloodMoon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Enemy {
	
	Player player;
	public boolean alive;
	public int width;
	public int height;
	public static int posX;
	public int posY;
	public int velX;
	public int velY;
	public int health;

	public Enemy (int x, int y, int w, int h, int hp, Player p) {
		alive = true;
		width = w; 
		height = h;
		posX = x;
		posY = y;
		velX = 0;
		velY = 0;
		player = p;
	}
	
	public void physics () {
		
	}
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.fillRect(posX, posY, width, height);

	}
	
	public void checkHit () {
		if (player.swordX>posX && player.swordX<posX+width) {
			if (player.swordY>posY && player.swordY<posY+height) {
				if (player.swordHit) {
					health--;
					System.out.println("aaa");
				}
			}
		}
	}
	
	public Rectangle hitbox ( ) {
		return (new Rectangle (posX, posY, width, height));
	}
}
