package view;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by user on 11-Feb-18.
 */
public class DraggablePanel extends JPanel {
	private volatile int draggedAtX, draggedAtY;
	public DraggablePanel()
	{
		super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				draggedAtX =e.getX();
				draggedAtY =e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getX()- draggedAtX + getLocation().x, e.getY()- draggedAtY + getLocation().y);
			}
		});
	}

}
