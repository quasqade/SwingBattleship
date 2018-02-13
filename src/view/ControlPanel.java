package view;

import model.ShipType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 11-Feb-18.
 */
public class ControlPanel extends JPanel {
	private GameFrame parent;

	public ControlPanel(GameFrame parent)
	{
		this.parent=parent;
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(); //nested panel is necessary, otherwise buttons are only placed horizontally
		panel.setBorder(BorderFactory.createTitledBorder("Place ship"));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.BOTH;
		for (ShipType type: ShipType.values()
			 )
		{
			if (type.equals(ShipType.SHELLED))
				continue;
			JButton but = new JButton(type.toString());
			panel.add(but, gbc);
			but.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					parent.setHoveringShip(type);
				}
			});
			gbc.gridy++;
		}
		add(panel, BorderLayout.CENTER);
	}

}
