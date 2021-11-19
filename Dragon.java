package DragonGame111121;

import javax.swing.ImageIcon;

/**
 * record all the status of the dragon
 */
public class Dragon {
	boolean isJumping = false;
	int currX = 60;
	int XEnd = 0;
	double currY = 200;
	int YEnd = 0;
	boolean goUp = true;
	String dragon1 = "DragonGamePic\\dragon1.png";
	ImageIcon dragon1ImageIcon = new ImageIcon(dragon1);

	public Dragon() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * if the dragon is not jumping, set isJumping to true
	 */
	public void pressSpace() {
		if (isJumping == false) {
			isJumping = true;
		}
	}

	/* change the y to make the jump */
	public void getY() {
		if (isJumping) {
			/* going up, y is decreasing */
			if (currY > 100 && goUp) {
				if (currY > 125) {
					currY -= 2;
				} else if (currY > 100) {
					currY -= 1;
				}
			} else {
				/* going down, y is increasing */
				goUp = false;
				if (currY < 175) {
					currY += 1.5;
				} else if (currY < 200) {
					currY += 2;
				}
			}
		}
		/* back to the ground, finish the jump */
		if (currY >= 200) {
			isJumping = false;
			goUp = true;
		}
	}

	/* calculate the area for detecting collision with tree */
	public void getDragonArea() {
		XEnd = currX + dragon1ImageIcon.getIconWidth();
		YEnd = (int) currY + dragon1ImageIcon.getIconHeight();
	}
}
