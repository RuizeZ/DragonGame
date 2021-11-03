package DragonGame110221;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * this class handles all the graph painting
 * 
 * @author imrui
 *
 */
public class GraphProcess extends Thread {
	BufferedImage UIBufferedImage;// bufferedImage for UI
	Graphics UIBufferedImageGraphics;// graphics for the UI
	Graphics UIFrameGraphics;
	String map = "DragonGamePic\\map.png";
	String map1 = "DragonGamePic\\map1.png";
	String tree1 = "DragonGamePic\\tree1.png";
	String tree2 = "DragonGamePic\\tree2.png";
	Random rand = new Random();
	int UIWidth, UIHeight;

	public GraphProcess(Graphics UIFrameGraphics, int UIWidth, int UIHeight, BufferedImage UIBufferedImage) {
		this.UIWidth = UIWidth;
		this.UIHeight = UIHeight;
		this.UIBufferedImage = UIBufferedImage;
		this.UIBufferedImageGraphics = UIBufferedImage.getGraphics();
		this.UIFrameGraphics = UIFrameGraphics;
	}

	public GraphProcess() {

	}

	@Override
	public void run() {
		int move = 0;
		super.run();
		long prevTime = -1000;
		ImageIcon mapImageIcon = new ImageIcon(map);
		ImageIcon mapImageIcon1 = new ImageIcon(map1);
		ImageIcon firstTempMapImageIcon = mapImageIcon;
		ImageIcon secondTempMapImageIcon = mapImageIcon1;

		while (true) {
			if (move != -UIWidth) {
				UIBufferedImageGraphics.drawImage(firstTempMapImageIcon.getImage(), move, 0, UIWidth, UIHeight, null);
				UIBufferedImageGraphics.drawImage(secondTempMapImageIcon.getImage(), UIWidth + move, 0, UIWidth,
						UIHeight, null);
				move--;
				/* randomly generate new tree object and put into tree array */
				int randtree = rand.nextInt(500);
				if (randtree < 3) {
					long currTime = System.currentTimeMillis();
					if (currTime - prevTime > 1000) {
						Tree newTree = new Tree(UIWidth);
						Tree.treeArrayList.add(newTree);
						prevTime = currTime;
					}

				}

				/*
				 * draw all the tree in the tree array
				 */
				for (int i = 0; i < Tree.treeArrayList.size(); i++) {
					Tree currTree = Tree.treeArrayList.get(i);
					UIBufferedImageGraphics.drawImage(currTree.treeImageIcon.getImage(), currTree.currLocationX--,
							currTree.currLocationY, currTree.treeImageIcon.getIconWidth(),
							currTree.treeImageIcon.getIconHeight(), null);
					/*
					 * remove current tree if it is out of bound l.e. currLocationX < 0
					 */
					if (currTree.currLocationX < -30) {
						Tree.treeArrayList.remove(currTree);
					}
				}
				UIFrameGraphics.drawImage(UIBufferedImage, 0, 0, UIWidth, UIHeight, null);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				move = 0;

				if (firstTempMapImageIcon == mapImageIcon) {
					firstTempMapImageIcon = mapImageIcon1;
				} else {
					firstTempMapImageIcon = mapImageIcon;
				}
				if (secondTempMapImageIcon == mapImageIcon) {
					secondTempMapImageIcon = mapImageIcon1;
				} else {
					secondTempMapImageIcon = mapImageIcon;
				}
			}
		}
	}
}
