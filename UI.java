package DragonGame090921;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

/**
 * this class set up all the graphics before the game starts
 * 
 * @author imrui
 *
 */

public class UI extends JFrame {
	BufferedImage UIBufferedImage;
	Graphics UIFrameGraphics;

	/**
	 * initial UI
	 */
	public void initUI() {
		// set the Jframe
		this.setSize(734, 286);
		this.setTitle("Dragon Jump!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		UIFrameGraphics = this.getGraphics();
		// set Buffer and its graphics
		UIBufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

		// start myGraphProcess thread to draw graphs
		GraphProcess myGraphProcess = new GraphProcess(UIBufferedImage, UIFrameGraphics, this.getWidth(),
				this.getHeight());
		myGraphProcess.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
