package DragonGame111121;

import java.awt.event.*;

public class KeyboardControl implements KeyListener, ActionListener {
	Dragon myDragon;
	public KeyboardControl(Dragon myDragon) {
		// TODO Auto-generated constructor stub
		this.myDragon = myDragon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			/*
			 * press space, make dragon jump
			 */
//			System.out.println("jump");
			myDragon.pressSpace();
		}

	}

}
