package DragonGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.text.AttributeSet.ColorAttribute;

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
	Dragon myDragon;
	String map = "DragonGamePic\\map.png";
	String map1 = "DragonGamePic\\map1.png";
	String tree1 = "DragonGamePic\\tree1.png";
	String tree2 = "DragonGamePic\\tree2.png";
	String dragon1 = "DragonGamePic\\dragon1.png";
	String dragon2 = "DragonGamePic\\dragon2.png";
	String dragon3 = "DragonGamePic\\dragon3.png";
	String gameOver = "DragonGamePic\\gameover.png";
	Random rand = new Random();
	int UIWidth, UIHeight;
	int dragonCount = 1;
	static boolean isCollision = false;

	public GraphProcess(Dragon myDragon, Graphics UIFrameGraphics, int UIWidth, int UIHeight,
			BufferedImage UIBufferedImage) {
		this.UIWidth = UIWidth;
		this.UIHeight = UIHeight;
		this.UIBufferedImage = UIBufferedImage;
		this.UIBufferedImageGraphics = UIBufferedImage.getGraphics();
		this.UIFrameGraphics = UIFrameGraphics;
		this.myDragon = myDragon;
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
		ImageIcon dragon1ImageIcon = new ImageIcon(dragon1);
		ImageIcon dragon2ImageIcon = new ImageIcon(dragon2);
		ImageIcon firstTempMapImageIcon = mapImageIcon;
		ImageIcon secondTempMapImageIcon = mapImageIcon1;
		ImageIcon currentDragonImageIcon = dragon1ImageIcon;
		ImageIcon dragon3ImageIcon = new ImageIcon(dragon3);
		ImageIcon gameOverImageIcon = new ImageIcon(gameOver);
		int count = 0, score = 0;
		while (true) {
			if (move != -UIWidth) {
				UIBufferedImageGraphics.drawImage(firstTempMapImageIcon.getImage(), move, 0, UIWidth, UIHeight, null);
				UIBufferedImageGraphics.drawImage(secondTempMapImageIcon.getImage(), UIWidth + move, 0, UIWidth,
						UIHeight, null);
				UIBufferedImageGraphics.drawImage(currentDragonImageIcon.getImage(), (int) myDragon.currX,
						(int) myDragon.currY, currentDragonImageIcon.getIconWidth(),
						currentDragonImageIcon.getIconHeight(), null);
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

					/* check for collision */
					if (currTree.currLocationX + 20 <= myDragon.currX + currentDragonImageIcon.getIconWidth()
							&& currTree.currLocationX + currTree.treeImageIcon.getIconWidth() - 5 >= myDragon.currX) {
//						System.out.println("collision check");
						myDragon.getDragonArea();
						if (myDragon.YEnd - 10 >= currTree.currLocationY) {
							/* game over */
							/* draw dead dragon */
							UIBufferedImageGraphics.drawImage(dragon3ImageIcon.getImage(), (int) myDragon.currX,
									(int) myDragon.currY, currentDragonImageIcon.getIconWidth(),
									currentDragonImageIcon.getIconHeight(), null);
							UIBufferedImageGraphics.drawImage(gameOverImageIcon.getImage(), 734 / 3, 286 / 4,
									gameOverImageIcon.getIconWidth(), gameOverImageIcon.getIconHeight(), null);
							isCollision = true;
						}
					}

					/*
					 * remove current tree if it is out of bound l.e. currLocationX < 0
					 */
					if (currTree.currLocationX < -30) {
						Tree.treeArrayList.remove(currTree);
						score += 5;
					}
				}
				count++;
				UIBufferedImageGraphics.setColor(Color.BLACK);
				UIBufferedImageGraphics
						.setFont(new Font(UIBufferedImageGraphics.getFont().getFontName(), Font.PLAIN, 15));
				if (count == 200) {
					UIBufferedImageGraphics.drawString(String.valueOf(score++), UIWidth - 80, 70);
					count = 0;
				} else {
					UIBufferedImageGraphics.drawString(String.valueOf(score), UIWidth - 80, 70);
				}

				UIFrameGraphics.drawImage(UIBufferedImage, 0, 0, UIWidth, UIHeight, null);
				if (isCollision) {
					return;
				}
				if (!myDragon.isJumping) {
					/* draw dragon alternatively */
					dragonCount++;
					if (dragonCount == 15) {
						dragonCount *= -1;
						currentDragonImageIcon = dragon2ImageIcon;
					} else if (dragonCount == 0) {
						currentDragonImageIcon = dragon1ImageIcon;
					}
				} else {

					/* set X and Y of the dragon, make the jump */
					myDragon.getY();
				}

				try {
					Thread.sleep(3);
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
