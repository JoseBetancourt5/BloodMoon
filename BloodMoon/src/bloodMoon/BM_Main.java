package bloodMoon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BM_Main {
	
	JFrame frame;
	Drawer d;
	
	public static void main (String [] args) {
		new BM_Main ();
	}
	
	public BM_Main () {
		frame = new JFrame ("BloodMoon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 350);
		//System.out.println("i");
		this.init();
	}
	
	public void init () {
		frame.setLocationRelativeTo(null);
		//frame.setLayout(new GridLayout(1,1,0,0));

		d = new Drawer();
		frame.add(d);

		//frame.addKeyListener(k);
		frame.setVisible(true);
	}

}
