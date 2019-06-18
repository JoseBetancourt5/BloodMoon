package bloodMoon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Player {

	KeyListener1 keyListener1;
	BufferedImage playerRight;
	BufferedImage playerLeft;
	BufferedImage sword;
	BufferedImage wand;
	BufferedImage healthPot;
	BufferedImage shield;
	BufferedImage lightning;
	BufferedImage bomb;


	
	public boolean gameOver;
	public int width;
	public int height;
	public static int posX;
	public int posY;
	public int velX;
	public int velY;
	private int direction;
	private int mouseDegree;
	int angle;
	int swordX;
	int swordY;
	int wandX;
	int wandY;
	public ArrayList <Enemy> enemies;
	public ArrayList <Bullet> bullets;
	public boolean swordHit;
	private int swordCount;
	public int health;
	public boolean wandShoot;
	public int wandCount;
	public static int healthPots;
	public static int barriers;
	private boolean shieldUp;
	private int shieldCounter;
	public static int bolts;
	private boolean boltOn;
	private int boltCounter;
	public static int bombs;
	private boolean bombActive;
	private int bombCounter;
	public boolean explode;
	public int bombX;
	public int bombY;

	




	public Player (ArrayList <Enemy> en, ArrayList <Bullet> b) {
		
		healthPots = 0;
		barriers = 0;
		shieldUp = false;
		shieldCounter = 0;
		bolts = 0;
		boltOn = false;
		boltCounter = 0;
		bombs = 0;
		bombActive = false;
		bombCounter = 0;
		explode = false;
		bombX = 0;
		bombY = 0;

		
		gameOver = false;
		width = 60; 
		height = 60;
		posX = 700;
		posY = 350;
		velX = 0;
		velY = 0;
		enemies = en;
		bullets = b;
		swordHit = false;
		swordCount = 0;
		health = 100;
		wandShoot = false;
		wandCount = 0;

		keyListener1 = new KeyListener1();
		
		try {
			playerRight = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\King Right (1).png"));
			playerLeft = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\King Left (1).png"));
			sword = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Sword.png"));
			wand = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Wand.png"));
			healthPot = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Health Potion.png"));
			shield = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Shield.png"));
			lightning = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Lightning.png"));
			bomb = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\bomb.png"));


		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (KeyListener1.mouseX>(posX+width*.4)+(1.2*width)/2) {
			direction =1;
		}
		else if (KeyListener1.mouseX<(posX-width*.5)+(1.2*width)/2) {
			direction =-1;
		}

		double angle = 0;
		
		swordX = -20;
		swordY = -20;
		wandX = -20;
		wandY = -20;
		
		g2.setStroke(new BasicStroke (2));
		g2.drawRect(posX, posY-25, width, 10);
		g2.setColor(Color.RED);
		g2.fillRect(posX+1, posY-24, (int)(((double)health/(100))*(width-2)), 8);

		if (shieldUp) {
			g2.setColor(Color.BLUE);
			g2.drawRect(posX -20, posY -10, width +40, height + 30);
			shieldCounter++;
			if (shieldCounter>150) {
				shieldUp = false;
				shieldCounter = 0;
			}
		}
		if (boltOn) {
			boltCounter++;
			if (boltCounter>300) {
				boltOn = false;
				boltCounter = 0;
			}
		}
		
		if (bombActive) {
			g2.drawImage(bomb, bombX, bombY, 20, 20, null);
			bombCounter++;
			if (bombCounter>200) {
				bombActive = false;
				explode = true;
				bombCounter = 0;
			}
		}
		
		
	    if(KeyListener1.numKey == 1) {
			if (direction>0) {
				g2.drawImage(playerRight, posX, posY, width, height, null);
		        g2.translate((int)(posX+width*.4)+(1.2*width)/2, (int)(posY-height*.3)+(1.2*height)*.75);
		        if (swordHit) {
		        	if (direction >0 && KeyListener1.mouseX>(posX+width*.4)+(1.2*width)/2) {
			        	angle = Math.atan((KeyListener1.mouseY-26-(posY-height*.3)-(1.2*height)*.75)/(KeyListener1.mouseX-(posX+width*.4)-(1.2*width)/2));
			        	g2.rotate(angle+Math.PI/2);
			        	swordX = (int)((posX+width*.4)+(1.2*width)/2+Math.sin(angle+Math.PI/2)*50);
			        	swordY = (int)((posY-height*.3)+(1.2*height)*.75-Math.cos(angle+Math.PI/2)*50);
		
		        	}
		        }
		
		
				g2.drawImage(sword, (int)-(1.3*width)/2, (int)-((1.4*height)*.75), (int)(width*1.4), (int)(height*1.5), null);
		
			}
			else {
				g2.drawImage(playerLeft, posX, posY, height, width, null);
				g2.translate((int)(posX-width*.5)+(1.2*width)/2, (int)(posY-height*.3)+(1.2*height)*.75);
		        if (swordHit) {
		        	if (direction <0 && KeyListener1.mouseX<(posX-width*.5)+(1.2*width)/2) {
			        	angle = Math.atan((KeyListener1.mouseY-26-(posY-height*.3)-(1.2*height)*.75)/(KeyListener1.mouseX-(posX-width*.5)-(1.2*width)/2));
			        	g2.rotate(angle+3*Math.PI/2);
			        	swordX = (int)((posX-width*.5)-(1.2*width)/2+70+Math.sin(angle+3*Math.PI/2)*50);
			        	swordY = (int)((posY-height*.3)+(1.2*height)*.75-Math.cos(angle+3*Math.PI/2)*50);
		        	}
		        }
		        
		        g2.drawImage(sword, (int)-(1.4*width)/2, (int)-((1.4*height)*.75), (int)(width*1.2), (int)(height*1.5), null);
		        
			}
	    }
        //this one
	    else if(KeyListener1.numKey == 2) {
			if (direction>0) {
				g2.drawImage(playerRight, posX, posY, width, height, null);
		        g2.translate((int)(posX+width*.4)+(1.2*width)/2, (int)(posY-height*.3)+(1.2*height)*.75);
		        if (wandShoot || boltOn) {
		        	if (direction >0 && KeyListener1.mouseX>(posX+width*.4)+(1.2*width)/2) {
			        	angle = Math.atan((KeyListener1.mouseY-26-(posY-height*.3)-(1.2*height)*.75)/(KeyListener1.mouseX-(posX+width*.4)-(1.2*width)/2));
			        	g2.rotate(angle+Math.PI/2);
			        	wandX = (int)((posX+width*.4)+(1.2*width)/2+Math.sin(angle+Math.PI/2)*42);
			        	wandY = (int)((posY-height*.3)+(1.2*height)*.75-Math.cos(angle+Math.PI/2)*42);
			        	bullets.add(new Bullet(wandX, wandY, 10*Math.cos(angle), 10*Math.sin(angle), 20, 20, true, this));
		        	}
		        }
	
	
	
		        g2.drawImage(wand, (int)-(1.2*width)/2, (int)-((1.2*height)*.75), (int)(width*1.2), (int)(height*1.2), null);
	
			}
			else {
				g2.drawImage(playerLeft, posX, posY, height, width, null);
				g2.translate((int)(posX-width*.5)+(1.2*width)/2, (int)(posY-height*.3)+(1.2*height)*.75);
		        if (wandShoot || boltOn) {
		        	if (direction <0 && KeyListener1.mouseX<(posX-width*.5)+(1.2*width)/2) {
			        	angle = Math.atan((KeyListener1.mouseY-26-(posY-height*.3)-(1.2*height)*.75)/(KeyListener1.mouseX-(posX-width*.5)-(1.2*width)/2));
			        	g2.rotate(angle+3*Math.PI/2);
			        	wandX = (int)((posX-width*.5)-(1.2*width)/2+70+Math.sin(angle+3*Math.PI/2)*42);
			        	wandY = (int)((posY-height*.3)+(1.2*height)*.75-Math.cos(angle+3*Math.PI/2)*42);
			        	bullets.add(new Bullet(wandX, wandY, -10*Math.cos(angle), -10*Math.sin(angle), 20, 20, true, this));
		        	}
		        }
		        
		        g2.drawImage(wand, (int)-(1.2*width)/2, (int)-((1.2*height)*.75), (int)(width*1.2), (int)(height*1.2), null);
		        
			}
			
		}
		else {
			if (direction>0) {
				g2.drawImage(playerRight, posX, posY, width, height, null);
				if (KeyListener1.numKey == 3 && healthPots > 0) {
					g2.drawImage(healthPot, posX+54, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && health !=100) {
						health = 100;
						healthPots--;
						keyListener1.numKey = 1;
					}
				}
				else if (KeyListener1.numKey == 4 && barriers > 0) {
					g2.drawImage(shield, posX+54, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && !shieldUp) {
						shieldUp = true;
						barriers--;
						keyListener1.numKey = 1;
					}
				}
				else if (KeyListener1.numKey == 5 && bolts > 0) {
					g2.drawImage(lightning, posX+54, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && !boltOn) {
						boltOn = true;
						bolts--;
						keyListener1.numKey = 2;
					}
				}
				else if (KeyListener1.numKey == 6 && bombs > 0) {
					g2.drawImage(bomb, posX+56, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && !bombActive) {
						bombActive = true;
						bombs--;
						bombX = posX +56;
						bombY = posY+20;
						keyListener1.numKey = 1;
					}
				}
	
	
			}
			else {
				g2.drawImage(playerLeft, posX, posY, height, width, null);
				if (KeyListener1.numKey == 3 && healthPots > 0) {
					g2.drawImage(healthPot, posX-12, posY+20, 20, 20, null);
					
					if (keyListener1.mousePressed && health !=100) {
						health = 100;
						healthPots--;
						keyListener1.numKey = 1;
					}
				}
				else if (KeyListener1.numKey == 4 && barriers > 0) {
					g2.drawImage(shield, posX-10, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && !shieldUp) {
						shieldUp = true;
						barriers--;
						keyListener1.numKey = 1;
					}
				}
				else if (KeyListener1.numKey == 5 && bolts > 0) {
					g2.drawImage(lightning, posX-8, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && !boltOn) {
						boltOn = true;
						bolts--;
						keyListener1.numKey = 2;
					}
				}
				else if (KeyListener1.numKey == 6 && bombs > 0) {
					g2.drawImage(bomb, posX-6, posY+20, 20, 20, null);
					if (keyListener1.mousePressed && !bombActive) {
						bombX = posX -6;
						bombY = posY+20;
						bombActive = true;
						bombs--;
						keyListener1.numKey = 1;
					}
				}
			}
			

		}
	  


	}
	
	public void physics () {

		if (keyListener1.keys[3]) {
			velX=5;
		}
		else if (keyListener1.keys[1]) {
			velX=-5;
		}
		else {
			velX = 0;
		}
		
		if (keyListener1.keys[2]) {
			velY=5;
		}
		else if (keyListener1.keys[0]) {
			velY=-5;
		}
		else {
			velY = 0;
		}
		
		posX += velX;
		posY += velY;
		
		
		
	}
	

	public void checkBoundaries () {
		if (posX >1320-width) {
			posX = 1320-width;
		}
		else if (posX<46) {
			posX = 46;
		}
		if (posY<54) {
			posY = 54;
		}
		else if (posY>650-height) {
			posY=650-height;
		}
	}
	
	
	public void checkSword () {
		if (keyListener1.mousePressed && swordCount ==0) {
			swordHit = true;
		}
		
		if (swordCount >0 || swordHit) {
			swordCount++;
		}
		
		if (swordCount >16) {
			swordCount = 0;
		}
		else if (swordCount >4) {
			swordHit = false;
		}

		
		
	}
	
	public void checkWand () {
		if (KeyListener1.mousePressed && wandCount ==0) {
			wandShoot = true;
		}
		
		if (wandCount >0 || wandShoot) {
			wandCount++;
		}
		
		if (wandCount >16) {
			wandCount = 0;
		}
		else if (wandCount >4) {
			wandShoot = false;
		}
		
		
	}
	
	public void checkEnemy () {
		if (!shieldUp) {
			for (int i = 0; i<enemies.size(); i++) {
				if (enemies.get(i).hitbox().intersects(this.hitbox())) {
					health--;
				}
				else if (enemies.get(i).spearX<posX+width && enemies.get(i).spearX>posX) {
					if (enemies.get(i).spearY<posY+height && enemies.get(i).spearY>posY) {
						health--;
					}
				}
			}
			
			for (int i = 0; i<bullets.size(); i++) {
				if (!bullets.get(i).isForPlayer && bullets.get(i).hitbox().intersects(this.hitbox())) {
					health-=5;
					bullets.remove(bullets.get(i));
				}
			}
		}
		
		
		
		
		if (health <0) {
			gameOver = true;
		}
			
			
	}
	
	public Rectangle hitbox ( ) {
		return (new Rectangle (posX, posY, width, height));
	}
	
}
