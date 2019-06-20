package bloodMoon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
	
	Player player;
	public boolean alive;
	public int width;
	public int height;
	public int posX;
	public int posY;
	public int velX;
	public int velY;
	public int health;
	public boolean isShooter;
	private boolean spearOut;
	public int spearX;
	public int spearY;
	
	BufferedImage shooterRight;
	BufferedImage shooterLeft;
	BufferedImage stabberRight;
	BufferedImage stabberLeft;
	BufferedImage spear;
	BufferedImage spearRight;


	
	//public int player.posX;
	//public int player.posY;

	public Enemy (int x, int y, int w, int h, int hp, boolean s, Player p) {
		alive = true;
		width = w; 
		height = h;
		posX = x;
		posY = y;
		velX = 0;
		velY = 0;
		health = hp;
		player = p;
		isShooter = s;
		spearOut = true;
		spearX = 0;
		spearY = 0;
		
		try {
			shooterRight = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Wyvern_Enemy.png"));
			shooterLeft = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Wyvern_Enemy_Left.png"));
			stabberRight = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Ghoul_Enemy.png"));
			stabberLeft = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Ghoul_Enemy_Left.png"));
			spear = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Spear.png"));
			spearRight = ImageIO.read(new File("C:\\Users\\ruler\\git\\Blood_Moon\\BloodMoon\\src\\Images\\Spear Right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void physics () {
		posX+=velX;
		posY+=velY;
	}
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
			
		if (isShooter) {
			if (posX<player.posX) {
				g2.drawImage(shooterRight, posX, posY, width, height, null);
			}
			else {
				g2.drawImage(shooterLeft, posX, posY, width, height, null);
			}
		}
		else {
			if (posX<player.posX) {
				g2.drawImage(stabberRight, posX, posY, width, height, null);
				if (spearOut) {
					g2.drawImage(spearRight, posX+65, posY+50, 90, 30, null);
					spearX = posX+155;
					spearY = posY+65;
				}
				else {
					g2.drawImage(spearRight, posX+30, posY+50, 90, 30, null);
					spearX = posX+120;
					spearY = posY+65;
				}
			}
			else {
				g2.drawImage(stabberLeft, posX, posY, width, height, null);
				if (spearOut) {
					g2.drawImage(spear, posX-55, posY+50, 90, 30, null);
					spearX = posX-55;
					spearY = posY+65;
				}
				else {
					g2.drawImage(spear, posX-20, posY+50, 90, 30, null);
					spearX = posX-20;
					spearY = posY+65;
				}
			}
		}

	}
	
	public void checkHit () {
		if (player.swordX>posX && player.swordX<posX+width) {
			if (player.swordY>posY && player.swordY<posY+height) {
				if (player.swordHit) {
					health-=8;
				}
			}
		}
		
	
		for (int i = 0; i<player.bullets.size(); i++) {
			if (player.bullets.get(i).hitbox().intersects(hitbox())) {
				if (player.bullets.get(i).isForPlayer) {
					health--;
					player.bullets.remove(player.bullets.get(i));
				}
			}
		}
		
		if (player.explode) {
			if (Math.sqrt(Math.pow(player.bombX-posX-width/2, 2) + Math.pow(player.bombY-posY-height/2,2))<250) {
				health -=51;
			}
		}
	
		
		if (health <0) {
			alive = false;
			double num = Math.random();
			if (num<0.02) 
				player.bombs++;
			else if (num <0.05)
				player.bolts++;
			else if (num <0.09)
				player.barriers++;
			else if (num <0.16) 
				player.healthPots++;
			
			player.enemies.remove(this);
		}
	}
	
	public void attack () {
		if (isShooter) {
			if (Math.random()<.01) {
	        	double angle = Math.atan(((double)(posY-player.posY-player.height/2))/(posX-player.posX-player.width/2));
	        	if (posX > player.posX+20)
	        		player.bullets.add(new Bullet(posX, posY, -10*Math.cos(angle), -10*Math.sin(angle), 20, 20, false, player));
	        	else 
	        		player.bullets.add(new Bullet(posX, posY, 10*Math.cos(angle), 10*Math.sin(angle), 20, 20, false, player));

			}
		}
		else {
			if (Math.random()<.01) {
				spearOut = !spearOut;
				if (spearOut && Math.random()<.5) {
					spearOut = !spearOut;
				}
			}
		}
	}
	
	 public void enemyFollow() {
		 if (!isShooter) {
			 if (Math.sqrt(Math.pow(player.posX+player.width/2-posX-width/2, 2) + Math.pow(player.posY+player.height/2-posY-height/2,2))<135) {
			 		if(player.posX < posX-50){
			             velX = 4;
			         }
			         else if (player.posX > posX+50){
			             velX = -4;
			         }
			 		
			         if(player.posY < posY - 50){
			             velY = 4;
			         }
			         else if(player.posY > posY + 50){
			             velY = -4;
			         }
		 	}
			 else if (Math.sqrt(Math.pow(player.posX+player.width/2-posX-width/2, 2) + Math.pow(player.posY+player.height/2-posY-height/2,2))<250) {
				 
				 if (posX <=player.posX && posY >=player.posY) {
				 	posX += 3;
				 	posY += 3;
				 }
				 else if (posX >=player.posX && posY >=player.posY) {
					posX += 3;
					posY -= 3;
				 }
				 else if (posX >= player.posX && posY <=player.posX) {
					 posX -= 3;
					 posY -= 3;
				 }
				 else {
					 posX -= 3;
					 posY += 3;
				 }
		 	}
		 	else {
		 		if(player.posX < posX-50){
		             velX = -(int)(Math.random()*3+3);
		         }
		         else if (player.posX > posX+50){
		             velX = (int)(Math.random()*3+4);
		         }
		 		
		         if(player.posY < posY - 50){
		             velY = -(int)(Math.random()*3+4);
		         }
		         else if(player.posY > posY + 50){
		             velY = (int)(Math.random()*3+4);
		         }
		 	}
		 }
		 else {
			 if (posX >=1306-width) {
					velY = 3;
				}
				else if (posX<=60) {
					velY = -3;
				}
				if (posY<=60) {
					velX = 3;
				}
				else if (posY>=640-height) {
					velX = -3;
				}
		 }
		 
	 
		
	}
	
	 
		public void checkBoundaries () {
			if (posX >1306-width) {
				posX = 1306-width;
			}
			else if (posX<60) {
				posX = 60;
			}
			if (posY<60) {
				posY = 60;
			}
			else if (posY>640-height) {
				posY=640-height;
			}
		}
	 
	public Rectangle hitbox ( ) {
		return (new Rectangle (posX, posY, width, height));
	}
}
