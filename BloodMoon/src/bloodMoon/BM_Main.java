package bloodMoon;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class BM_Main {
	
	static JFrame frame;
	Drawer d;
	
	public static void main (String [] args) {
		new BM_Main ();
	}
	
	public BM_Main () {

		frame = new JFrame ("BloodMoon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 750);

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


		/*AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinousAudioDataStream loop = null;
		
		try {
			BGM = new AudioStream (new FileInputStream("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\GOT Music.mp3"));
			MD = BGM.getData();
			loop = new ContinousAudioDataStream(MD);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MGP.start(loop);*/
		
		try {
			File file = new File("C:\\Users\\ruler\\git\\BloodMoon2\\BloodMoon\\src\\Images\\GOT Music.wav");
			Clip clip = AudioSystem.getClip();
		    clip.open(AudioSystem.getAudioInputStream(file));
		    clip.start();
		    Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
			 
		
	}

}
