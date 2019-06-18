package bloodMoon;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyListener1 implements KeyListener, MouseListener {

	public static boolean keys [];
	public static int numKey;
	public static int key;
	public static int mouseX;
	public static int mouseY;
	public static boolean mousePressed;

	public KeyListener1 () {
		key = 0;
		keys = new boolean [4];
		numKey = 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		if (e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
			keys[3]=true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
			keys[2]=true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) {
			keys[1]=true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
			keys[0]=true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_1) {
			numKey = 1;
		}
		else if (e.getKeyCode()==KeyEvent.VK_2) {
			numKey = 2;
		}
		else if (e.getKeyCode()==KeyEvent.VK_3) {
			numKey = 3;
		}
		else if (e.getKeyCode()==KeyEvent.VK_4) {
			numKey = 4;
		}
		else if (e.getKeyCode()==KeyEvent.VK_5) {
			numKey = 5;
		}
		else if (e.getKeyCode()==KeyEvent.VK_6) {
			numKey = 6;
		}
	}

	public boolean [] getKeys () {
		return keys;
	}
	
	public int getKey () {
		return key;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
			keys[3]=false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
			keys[2]=false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) {
			keys[1]=false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
			keys[0]=false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		mouseX = e.getX();
		mouseY = e.getY();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;

	}
}

