package DragonGame090621;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * this class set up all the graphics before the game starts
 * 
 * @author imrui
 *
 */

public class UI extends JFrame {
	BufferedImage UIBufferedImage;
	Graphics UIGraphics;

	/**
	 * initial UI
	 */
	public void initUI() {
		// set the Jframe
		this.setSize(734, 286);
		this.setTitle("Dragon Jump!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		UIBufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		UIGraphics = UIBufferedImage.getGraphics();
		GraphProcess myGraphProcess = new GraphProcess(UIGraphics);
		myGraphProcess.loadGraph();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(UIBufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI myUI = new UI();
		myUI.initUI();
	}

}
