package bloodMoon;

import java.awt.Color;
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

	
	public Drawer () {

		t.start();
		keyListener = new KeyListener1();
		
		//items = new ArrayList <Item>();
		enemies = new ArrayList <Enemy>();
		
		hud = new HUD();
		
		player = new Player(enemies);
		
		enemies.add(new Enemy (300, 300, 100, 100, 100, player));
		enemies.add(new Enemy (300, 700, 100, 100, 100, player));

		enemies.add(new Enemy (300, 100, 100, 100, 100, player));
		enemies.add(new Enemy (300, 200, 100, 100, 100, player));
		enemies.add(new Enemy (400, 600, 100, 100, 100, player));
		enemies.add(new Enemy (1000, 100, 100, 100, 100, player));

		enemies.add(new Enemy (200, 200, 100, 100, 100, player));
		enemies.add(new Enemy (300, 600, 100, 100, 100, player));
		enemies.add(new Enemy (1100, 100, 100, 100, 100, player));
		


		
		//mario = new Mario(blocks);
		background = new Background ();
		

		

		
		addMouseListener(keyListener);
		addKeyListener(keyListener);
		setFocusable(true);
	}
	
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color (0, 0, 100));
		

		background.paint(g2);
		hud.paint(g2);


		g2.setColor(new Color (0, 0, 255));

		//for (int i = 0; i<items.size(); i ++) {
		//	items.get(i).paint(g2);
		//	items.get(i).physics();
		//}
		
		for (int i = 0; i<enemies.size(); i ++) {
			if (enemies.get(i).alive) {
				enemies.get(i).checkBoundaries();
				enemies.get(i).enemyFollow();
				enemies.get(i).physics();
				enemies.get(i).checkHit();
				enemies.get(i).paint(g2);
			}
		}
		
		g2.setColor(new Color (0, 0, 0));

		this.updateMouse();
		player.checkSword();
		player.physics();
		player.checkBoundaries();
		player.checkEnemy();
		player.paint(g2);

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
