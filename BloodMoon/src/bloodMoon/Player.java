package bloodMoon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
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


	
	public boolean alive;
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
	public ArrayList <Enemy> enemies;
	public boolean swordHit;
	private int swordCount;
	public int health;




	public Player (ArrayList <Enemy> en) {
		
		alive = true;
		width = 60; 
		height = 60;
		posX = 0;
		posY = 0;
		velX = 0;
		velY = 0;
		enemies = en;
		swordHit = false;
		swordCount = 0;
		health = 100;

		keyListener1 = new KeyListener1();

		
		try {
			playerRight = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\King Right (1).png"));
			playerLeft = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\King Left (1).png"));
			sword = ImageIO.read(new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\Sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;


		if (keyListener1.mouseX>(posX+width*.4)+(1.2*width)/2) {
			direction =1;
		}
		else if (keyListener1.mouseX<(posX-width*.5)+(1.2*width)/2) {
			direction =-1;
		}

		double angle = 0;
		
		swordX = -1;
		swordY = -1;
		
		if (direction>0) {
			g2.drawImage(playerRight, posX, posY, width, height, null);
	        g2.translate((int)(posX+width*.4)+(1.2*width)/2, (int)(posY-height*.3)+(1.2*height)*.75);
	        if (swordHit) {
	        	if (direction >0 && keyListener1.mouseX>(posX+width*.4)+(1.2*width)/2) {
		        	angle = Math.atan((keyListener1.mouseY-26-(posY-height*.3)-(1.2*height)*.75)/(keyListener1.mouseX-(posX+width*.4)-(1.2*width)/2));
		        	g2.rotate(angle+Math.PI/2);
		        	swordX = (int)((posX+width*.4)+(1.2*width)/2+Math.sin(angle+Math.PI/2)*42);
		        	swordY = (int)((posY-height*.3)+(1.2*height)*.75-Math.cos(angle+Math.PI/2)*42);

	        	}
	        }

			g2.drawImage(sword, (int)-(1.2*width)/2, (int)-((1.2*height)*.75), (int)(width*1.2), (int)(height*1.2), null);

		}
		else {
			g2.drawImage(playerLeft, posX, posY, height, width, null);
			g2.translate((int)(posX-width*.5)+(1.2*width)/2, (int)(posY-height*.3)+(1.2*height)*.75);
	        if (swordHit) {
	        	if (direction <0 && keyListener1.mouseX<(posX-width*.5)+(1.2*width)/2) {
		        	angle = Math.atan((keyListener1.mouseY-26-(posY-height*.3)-(1.2*height)*.75)/(keyListener1.mouseX-(posX-width*.5)-(1.2*width)/2));
		        	g2.rotate(angle+3*Math.PI/2);
		        	swordX = (int)((posX-width*.5)-(1.2*width)/2+70+Math.sin(angle+3*Math.PI/2)*42);
		        	swordY = (int)((posY-height*.3)+(1.2*height)*.75-Math.cos(angle+3*Math.PI/2)*42);
	        	}
	        }


	        
			g2.drawImage(sword, (int)-(1.2*width)/2, (int)-((1.2*height)*.75), (int)(width*1.2), (int)(height*1.2), null);
		}
		
		//System.out.println("x:" + swordX);
		//System.out.println("y:" + swordY);

	}
	
	public void physics () {

		if (keyListener1.keys[3]) {
			velX=4;
		}
		else if (keyListener1.keys[1]) {
			velX=-4;
		}
		else {
			velX = 0;
		}
		
		if (keyListener1.keys[2]) {
			velY=4;
		}
		else if (keyListener1.keys[0]) {
			velY=-4;
		}
		else {
			velY = 0;
		}
		
		posX += velX;
		posY += velY;
		
		
		
	}
	

	public void checkBoundaries () {
		if (posX >1400) {
			posX = 1400;
			velX = 0;
		}
		else if (posX<0) {
			posX = 0;
			velX = 0;
		}
		if (posY<0) {
			posY = 0;
			velY = 0;
		}
		else if (posY>700-height) {
			posY=(int)(700-height);
			velY=0;
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
	
	public void checkEnemy () {
		for (int i = 0; i<enemies.size(); i++) {
			if (enemies.get(i).hitbox().intersects(this.hitbox())) {
				//System.out.println("d");
			}
		}
	}
	
	public Rectangle hitbox ( ) {
		return (new Rectangle (posX, posY, width, height));
	}
	
}
