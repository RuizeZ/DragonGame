package DragonGame111021;

/**
 * record all the status of the dragon
 */
public class Dragon {
	static boolean isJumping = false;
	static int currX = 60;
	static double currY = 200;
	static boolean goUp = true;

	public Dragon() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * if the dragon is not jumping, set isJumping to true
	 */
	public static void pressSpace() {
		if (isJumping == false) {
			isJumping = true;
		}
	}

	public static void getY() {
		if (isJumping) {
			if (currY > 100 && goUp) {
				if (currY > 125) {
					currY -= 2;
				} else if (currY > 100) {
					currY -= 1;
				}
			} else {
				goUp = false;
				if (currY < 175) {
					currY += 1;
				} else if (currY < 200) {
					currY += 2;
				}
			}
		}
		if (currY >= 200) {
			isJumping = false;
			goUp = true;
		}
	}
}
