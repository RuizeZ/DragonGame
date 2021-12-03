package DragonGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseControl implements MouseListener {
	UI myUI;

	public MouseControl(UI myUI) {
		// TODO Auto-generated constructor stub
		this.myUI = myUI;
	}

	public MouseControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getX() >= 335 && e.getX() <= 361 && e.getY() >= 135 && e.getY() <= 164 && GraphProcess.isCollision) {
			GraphProcess.isCollision = false;
			myUI.newGame();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
