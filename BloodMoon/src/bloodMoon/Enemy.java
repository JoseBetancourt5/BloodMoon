package bloodMoon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

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
	
	//public int player.posX;
	//public int player.posY;

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
		posX+=velX;
		posY+=velY;
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
					if (health <0) {
						alive = false;
					}
				}
			}
		}
	}
	
	 public void enemyFollow() {
		 
		 if (Math.sqrt(Math.pow(player.posX+player.width/2-posX-width/2, 2) + Math.pow(player.posY-player.height/2-posY-height/2,2))<155) {
		 		if(player.posX < posX-50){
		             velX = 5;
		         }
		         else if (player.posX > posX+50){
		             velX = -5;
		         }
		 		
		         if(player.posY < posY - 50){
		             velY = 5;
		         }
		         else if(player.posY > posY + 50){
		             velY = -5;
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
	             velX = -(int)(Math.random()*3+4);
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
		 
	 
		 /*	 		if(player.posX < posX-50){
	             velX = -((int)(Math.random()*2))+3;
	         }
	         else if (player.posX > posX+50){
	             velX = (int)(Math.random()*2)+3;
	         }
	 		
	         if(player.posY < posY - 50){
	             velY = -((int)(Math.random()*2)+3);
	         }
	         else if(player.posY > posY + 50){
	             velY = (int)(Math.random()*2)+3;
	         }*/
		 
	     /*if(player.posX < posX){
             velX = -1;
         }
         else{
             velX = 1;
         }
         if(player.posY < posY){
             velY = -1;
         }
         else{
             velY = 1;
         }*/
	}
	
	 
		public void checkBoundaries () {
			if (posX >1400) {
				posX = 1400;
				//velX = 0;
			}
			else if (posX<0) {
				posX = 0;
				//velX = 0;
			}
			if (posY<0) {
				posY = 0;
				//velY = 0;
			}
			else if (posY>700-height) {
				posY=(int)(700-height);
				//velY=0;
			}
		}
	 
	public Rectangle hitbox ( ) {
		return (new Rectangle (posX, posY, width, height));
	}
}
