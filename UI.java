package DragonGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * this class set up all the graphics before the game starts
 * 
 * @author imrui
 *
 */

public class UI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage UIBufferedImage;
	Graphics UIFrameGraphics;
	JLabel timeLabel;

	/**
	 * initial UI
	 */
	public void initUI() {
		// set the Jframe
		this.setSize(734, 286);
		this.setTitle("Dragon Jump!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		timeLabel = new JLabel("Your time left:  ");
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setVisible(true);
		newGame();
	}

	// start myGraphProcess thread to draw graphs
	public void newGame() {
		// set Buffer and its graphics
		Dragon myDragon = new Dragon();
		UIFrameGraphics = this.getGraphics();
		UIBufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		GraphProcess myGraphProcess = new GraphProcess(myDragon, UIFrameGraphics, this.getWidth(), this.getHeight(),
				UIBufferedImage);
		KeyboardControl keyboard = new KeyboardControl(myDragon);
		MouseControl mouse = new MouseControl(this);
		this.addMouseListener(mouse);
		this.addKeyListener(keyboard);
		Tree.treeArrayList = new ArrayList<>();
		myGraphProcess.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
//		g.drawImage(UIBufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI myUI = new UI();
		myUI.initUI();
	}

}
