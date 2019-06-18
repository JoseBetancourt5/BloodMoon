package bloodMoon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Drawer extends JPanel implements ActionListener {

	Timer t = new Timer (20, this);
	Background background;
	Player player;
	KeyListener1 keyListener;
	HUD hud;
	//public ArrayList <Item> items;
	public ArrayList <Enemy> enemies;
	public ArrayList <Bullet> bullets;
	int wave;
    Font font;


	
	public Drawer () {

		t.start();
		keyListener = new KeyListener1();
		wave = 1;
		
		//items = new ArrayList <Item>();
		enemies = new ArrayList <Enemy>();
		bullets = new ArrayList <Bullet>();
		
		hud = new HUD(player);
		
		player = new Player(enemies, bullets);
		
		/*enemies.add(new Enemy (300, 300, 100, 100, 50, false, player));
		enemies.add(new Enemy (300, 700, 100, 100, 50, false, player));

		enemies.add(new Enemy (300, 100, 100, 100, 50, false, player));
		enemies.add(new Enemy (300, 200, 100, 100, 50, false, player));
		enemies.add(new Enemy (400, 600, 100, 100, 50, false, player));
		enemies.add(new Enemy (1000, 100, 100, 100, 50, false, player));

		enemies.add(new Enemy (200, 200, 100, 100, 50, false, player));
		enemies.add(new Enemy (300, 600, 100, 100, 50, false, player));
		enemies.add(new Enemy (1100, 100, 100, 100, 50, false, player));
		
		enemies.add(new Enemy (60, 60, 100, 100, 50, true, player));
		enemies.add(new Enemy (1206, 60, 100, 100, 50, true, player));
		enemies.add(new Enemy (1266, 640, 100, 100, 50, true, player));
		enemies.add(new Enemy (60, 640, 100, 100, 50, true, player));*/

		enemies.add(new Enemy (300, 300, 100, 100, 50, false, player));


		
		//mario = new Mario(blocks);
		background = new Background ();
		

		

		
		addMouseListener(keyListener);
		addKeyListener(keyListener);
		setFocusable(true);
	}
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
        font = new Font("InfoErrataMsg", Font.BOLD, 60);
        g2.setFont(font);

		background.paint(g2);
		


		if (!player.gameOver) {
			g2.drawString("Wave: " + wave, 1100, 46);

			hud.paint(g2);

	
	
			g2.setColor(new Color (0, 0, 255));
	
			//for (int i = 0; i<items.size(); i ++) {
			//	items.get(i).paint(g2);
			//	items.get(i).physics();
			//}
			
			if (enemies.size()!=0) {
				for (int i = 0; i<enemies.size(); i ++) {
					int size = enemies.size();
					enemies.get(i).attack();
					enemies.get(i).paint(g2);
					enemies.get(i).checkBoundaries();
					enemies.get(i).enemyFollow();
					enemies.get(i).physics();
					enemies.get(i).checkHit();
					if (enemies.size()<size)
						i--;
				}
				player.explode = false;
			}
			else {
				wave++;
				player.health=100;
				if (wave <=5) {
					for (int i =0; i <wave; i ++) {
						enemies.add(new Enemy ((int)(Math.random()*1000+120), (int)(Math.random()*600+60), 100, 100, 50, false, player));
					}
				}
				else if (wave <=15) {
					if (wave >=6) {
						enemies.add(new Enemy (60, 60, 100, 100, 50, true, player));
					}
					if (wave >=8) {
						enemies.add(new Enemy (1206, 60, 100, 100, 50, true, player));
					}
					if (wave >=10) {
						enemies.add(new Enemy (1266, 640, 100, 100, 50, true, player));
					}
					if (wave >=12) {
						enemies.add(new Enemy (60, 640, 100, 100, 50, true, player));
					}
					int count = enemies.size();
					for (int i =0; i <wave-count; i ++) {
						enemies.add(new Enemy ((int)(Math.random()*1000+120), (int)(Math.random()*600+60), 100, 100, 50, false, player));
					}
				}
				else {
					enemies.add(new Enemy (60, 60, 100, 100, wave*4, true, player));
					enemies.add(new Enemy (1206, 60, 100, 100, wave*4, true, player));
					enemies.add(new Enemy (1266, 640, 100, 100, wave*4, true, player));
					enemies.add(new Enemy (60, 640, 100, 100, wave*4, true, player));
					int count = enemies.size();
					for (int i =0; i <12; i ++) {
						enemies.add(new Enemy ((int)(Math.random()*1000+120), (int)(Math.random()*600+60), 100, 100, wave*4, false, player));
					}
				}
			} 
			
			
			for (int i = 0; i<bullets.size(); i ++) {
				bullets.get(i).physics();
				bullets.get(i).paint(g2);
				bullets.get(i).checkBoundaries();
			}
		
			
			g2.setColor(new Color (0, 0, 0));
	
			this.updateMouse();
			player.checkSword();
			player.checkWand();
			player.physics();
			player.checkBoundaries();
			player.checkEnemy();
			player.paint(g2);
		}
		else {
			g2.setColor(Color.BLACK);
			g2.drawString("GAME OVER", 480, 150);
			g2.drawString("MAXIMUM WAVE: " + wave, 390, 300);
			if (wave <=15)
				g2.drawString("ENEMIES KILLED: " + (enemyCount(wave-1)+wave-enemies.size()), 385, 450);
			else {
				g2.drawString("ENEMIES KILLED: " + (enemyCount(wave-1)+15-enemies.size()), 385, 450);
			}
		}
	}
	
	private int enemyCount (int w) {
		if (w>0)
			return w + enemyCount(--w);
		else 
			return 0;
	}
	
	public void updateMouse () {
		keyListener.mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
		keyListener.mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
