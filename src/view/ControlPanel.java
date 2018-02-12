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
	public ControlPanel()
	{
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(); //nested panel is necessary, otherwise buttons are only placed horizontally
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(5,5,5,5);
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
					processPlacingClick(type);
				}
			});
			gbc.gridy++;
		}
		add(panel, BorderLayout.CENTER);
	}

	private void processPlacingClick(ShipType type)
	{

	}
}
