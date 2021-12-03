package DragonGame;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Tree class Each object is a tree on the graph. Each object contains info
 * about the current location for drawing.
 */
public class Tree {
	static ArrayList<Tree> treeArrayList = new ArrayList<>();
	int currLocationX;
	int currLocationY;
	Random rand = new Random();
	String tree1 = "DragonGamePic\\tree1.png";
	String tree2 = "DragonGamePic\\tree2.png";
	ImageIcon treeImageIcon;

	public Tree() {
	}

	public Tree(int UIWidth) {
		this.currLocationX = UIWidth;
		/*
		 * randomly decide if this is a tree1 or tree2
		 */
		if (rand.nextInt(2) == 0) {
			treeImageIcon = new ImageIcon(tree1);
			currLocationY = 196;
		} else {
			treeImageIcon = new ImageIcon(tree2);
			currLocationY = 205;
		}
	}
}
