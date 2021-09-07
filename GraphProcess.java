package DragonGame090621;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * this class handles all the graph painting
 * 
 * @author imrui
 *
 */
public class GraphProcess {
	BufferedImage graphBufferedImage;
	Graphics UIGraphics;
	int[][] pixelArray;

	public GraphProcess(Graphics UIGraphics) {
		this.UIGraphics = UIGraphics;
	}

	public GraphProcess() {

	}

	/**
	 * paint the graph before the game starts
	 */
	public void loadGraph() {
		getPixel();
		// read pixel from pixelArray, draw the graph on the UI
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[0].length; j++) {
				UIGraphics.setColor(new Color(pixelArray[i][j]));
				UIGraphics.fillRect(j, i, 1, 1);
			}
		}
	}

	/**
	 * get pixel from the graph file
	 */
	private void getPixel() {
		// read the graph file and store in the buffer
		File file = new File("DragonGamePic\\map.png");
		try {
			graphBufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int row = graphBufferedImage.getHeight();
		int column = graphBufferedImage.getWidth();
		pixelArray = new int[row][column];
		// get RGB from the file and store in the pixelArray
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				pixelArray[i][j] = graphBufferedImage.getRGB(j, i);
			}
		}
	}
}
