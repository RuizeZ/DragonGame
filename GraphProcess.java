package DragonGame090921;

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
public class GraphProcess extends Thread {
	BufferedImage graphBufferedImage;// store the input file(graph)
	BufferedImage UIBufferedImage;// bufferedImage for UI
	Graphics UIBufferGraphics;// graphics for UIBufferedImage

	Graphics UIFrameGraphics;// graphics for the UI
	int[][] mapPixelArray;
	int[][] map1PixelArray;
	int[][] pixelArray = null;
	String file1 = "DragonGamePic\\map.png";
	String file2 = "DragonGamePic\\map1.png";
	int UIWidth, UIHeight;

	public GraphProcess(BufferedImage UIBufferedImage, Graphics UIFrameGraphics, int UIWidth, int UIHeight) {
		this.UIBufferedImage = UIBufferedImage;
		this.UIBufferGraphics = this.UIBufferedImage.getGraphics();
		this.UIFrameGraphics = UIFrameGraphics;
		this.UIWidth = UIWidth;
		this.UIHeight = UIHeight;
		getPixel(file1);
		getPixel(file2);
	}

	public GraphProcess() {

	}

	@Override
	public void run() {
		int move = 1;
		int count = 0;
		super.run();
		loadGraph(0, 0);
		UIFrameGraphics.drawImage(UIBufferedImage, 0, 0, UIWidth, UIHeight, null);
		while (true) {
			if (count % 2 == 0) {
				loadGraph(move, count);
				UIFrameGraphics.drawImage(UIBufferedImage, 0, 0, UIWidth, UIHeight, null);
			} else {
				loadGraph(move, count);
				UIFrameGraphics.drawImage(UIBufferedImage, 0, 0, UIWidth, UIHeight, null);
			}
			move++;
			if (move == UIWidth) {
				move = 1;
				count++;
			}
		}
	}

	/**
	 * paint the map.png before the game starts
	 */
	public void loadGraph(int move, int count) {
		int secStartIndex;
		if (count % 2 == 0) {
			pixelArray = mapPixelArray;
			for (int i = 0; i < pixelArray.length; i++) {
				for (int j = move; j < pixelArray[0].length; j++) {
					UIBufferGraphics.setColor(new Color(pixelArray[i][j]));
					UIBufferGraphics.fillRect(j - move, i, 1, 1);
				}
			}
			pixelArray = map1PixelArray;
			for (int i = 0; i < pixelArray.length; i++) {
				for (int j = 0; j < move; j++) {
					UIBufferGraphics.setColor(new Color(pixelArray[i][j]));
					UIBufferGraphics.fillRect(pixelArray[0].length - move + j, i, 1, 1);
				}
			}

		} else {
			pixelArray = map1PixelArray;
			for (int i = 0; i < pixelArray.length; i++) {
				for (int j = move; j < pixelArray[0].length; j++) {
					UIBufferGraphics.setColor(new Color(pixelArray[i][j]));
					UIBufferGraphics.fillRect(j - move, i, 1, 1);
				}
			}
			pixelArray = mapPixelArray;
			for (int i = 0; i < pixelArray.length; i++) {
				for (int j = 0; j < move; j++) {
					UIBufferGraphics.setColor(new Color(pixelArray[i][j]));
					UIBufferGraphics.fillRect(j + pixelArray[0].length - move, i, 1, 1);
				}
			}
		}

	}

	/**
	 * get pixel from the graph file
	 */
	private void getPixel(String fileName) {
		// read the graph file and store in the buffer
		File file = new File(fileName);
		try {
			graphBufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int row = graphBufferedImage.getHeight();
		int column = graphBufferedImage.getWidth();
		if (fileName == file1) {
			mapPixelArray = new int[row][column];
			pixelArray = mapPixelArray;
		} else if (fileName == file2) {
			map1PixelArray = new int[row][column];
			pixelArray = map1PixelArray;
		}
		// get RGB from the file and store in the pixelArray
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				pixelArray[i][j] = graphBufferedImage.getRGB(j, i);
			}
		}
	}
}
